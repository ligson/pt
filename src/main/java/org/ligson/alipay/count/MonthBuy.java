package org.ligson.alipay.count;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;

public class MonthBuy {
	public static void main(String[] args) throws Exception {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("./Result_20170117.xls"));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();
		System.out.println(rowNum);
		List<BuyFlow> buyFlows = new ArrayList<>();
		for (int i = 0; i < rowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			short cellNum = row.getLastCellNum();
			for (int j = 0; j < cellNum; j++) {
				HSSFCell cell = row.getCell(j);
				@SuppressWarnings("deprecation")
				CellType cellType = cell.getCellTypeEnum();
				if (cellType == CellType.NUMERIC) {
					if (i > 4 && i < 544 && j == 0) {
						System.out.print(DateFormatUtils.format(cell.getDateCellValue(), "yyyy-MM-dd HH:mm:ss") + "\t");
					} else {
						System.out.print(cell.getNumericCellValue() + "\t");
					}
				} else if (cellType == CellType.STRING) {
					System.out.print(cell.getStringCellValue() + "\t");
				}

			}
			if (i > 4 && i < 544) {
				BuyFlow buyFlow = new BuyFlow(row.getCell(0).getDateCellValue(), row.getCell(1).getNumericCellValue(),
						row.getCell(2).getStringCellValue(), row.getCell(3).getNumericCellValue());
				buyFlows.add(buyFlow);
			}
			System.out.println("");
		}
		wb.close();
		System.out.println(buyFlows);

		Map<String, List<BuyFlow>> map = new HashMap<>();
		for (BuyFlow buyFlow : buyFlows) {
			String date = DateFormatUtils.format(buyFlow.getDate(), "yyyy-MM-dd HH:mm:ss");
			String key = date.substring(0, 7);
			if (!map.containsKey(key)) {
				map.put(key, new ArrayList<>());
			}
			map.get(key).add(buyFlow);
			System.out.println(buyFlow.getMoney());
		}

		List<MonthFlow> monthFlows = new ArrayList<>();
		String[] outTypes = new String[] { "消费", "转出到银行卡", "基金定投" };
		for (String key : map.keySet()) {
			List<BuyFlow> buys = map.get(key);
			double outMoney = 0;
			double inMoney = 0;
			for (BuyFlow buyFlow : buys) {
				if (Arrays.binarySearch(outTypes, buyFlow.getType()) >= 0) {
					outMoney += buyFlow.getMoney();
				} else {
					inMoney += buyFlow.getMoney();
				}
			}
			System.out.println(key + "====" + outMoney + "===" + inMoney);
			monthFlows.add(new MonthFlow(Integer.parseInt(key.substring(0, 4)), Integer.parseInt(key.substring(5)),
					outMoney, inMoney));
		}

		Collections.sort(monthFlows, new MonthFlowCompator());
		double totalOut = 0;
		double totalIn = 0;
		System.out.println("===============================");
		System.out.println("=         2016年财务预算                 =");
		System.out.println("===============================");
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");
		for (MonthFlow monthFlow : monthFlows) {
			if (monthFlow.getYear() == 2016) {
				System.out.println(monthFlow.getYear() + "年" + monthFlow.getMonth() + "月\t消费:"
						+ df.format(monthFlow.getOutMoney()) + "\t收入:" + df.format(monthFlow.getInMoney()));
				totalOut += monthFlow.getOutMoney();
				totalIn += monthFlow.getInMoney();
			}
		}
		System.out.println("2016年总收入：" + df.format(totalIn));
		System.out.println("2016年月平均收入：" + df.format(totalIn / 12));
		System.out.println("2016年总消费：" + df.format(totalOut));
		System.out.println("2016年月平均消费：" + df.format(totalOut / 12));
		System.out.println("2016年总消费(排除买车12w)：" + df.format(totalOut - 120000));
		System.out.println("2016年月平均消费(排除买车12w)：" + df.format((totalOut - 120000) / 12));

	}
}

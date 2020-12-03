package com.guigu.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.guigu.write.WriteFileStandard;

/**
 * Hello world!
 *
 */
public class AppFileCheckOrder {
	public static void main(String[] args) {
//		System.out.println("Hello World!");
		String filePath = "D:\\Work\\text\\jieguo.sql";
//		String filePath = "D:\\Work\\text\\alldata_check.json";
		List<String> readFileByLineList = readFileByLine(filePath);
		getOrder(readFileByLineList);
//		getTypeNum(filePath);
	}

	public static void getOrder(List<String> readFileByLineList) {

		List<String> arrayList = new ArrayList<>();

		for (String lineString : readFileByLineList) {
			String reg = "\\s+";
//			System.out.println(lineString);
			String[] split11 = lineString.split(reg);
			System.err.println(split11[0]);
			String num0 = split11[0];
//			Integer num = Integer.valueOf(num0) + 350;
			Integer num = Integer.valueOf(num0);

			String reg22 = "\\d+\\s+";
			String[] split22 = lineString.split(reg22);
			if (split22 != null) {
				System.err.println(split22[1]);
			}
			String string = split22[1];
			String newValue = num + "===" + string;
			arrayList.add(newValue);
//			Map<Integer, String> treeMap = new TreeMap<>();
//			treeMap.put(num + 350, string);
//			for (Entry<Integer, String> mapEntry : treeMap.entrySet()) {
//				Integer key = mapEntry.getKey();
//				String value = mapEntry.getValue();
//				newValue = key + value;
//			}
//			Pattern p = Pattern.compile(reg);
//	        Matcher m = p.matcher(lineString);
//			if (m.matches()) {
//				String group = m.group(0);
//				System.err.println(group);
////				String[] split = lineString.split(reg);
////				System.out.println(split[1]);
//			}
//			boolean matches = lineString.matches("^\\d+\\s+");
		}
		String outFilePath = "D:\\Work\\text\\newjieguo.sql";
		WriteFileStandard.writeToFile(outFilePath, true, arrayList);
	}

	public static void getTypeNum(String filePath) {

		List<String> arrayList = new ArrayList<>();
		String typeString1 = "起诉";
		String typeString2 = "投资";
		String typeString3 = "质押";
		String typeString4 = "高管减持";
		String typeString5 = "股份股权转让";
		arrayList.add(typeString1);
		arrayList.add(typeString2);
		arrayList.add(typeString3);
		arrayList.add(typeString4);
		arrayList.add(typeString5);

		Set<Integer> hashSet = new HashSet<>();

		for (String typeString : arrayList) {
			Set<Integer> readFileByLine = readFileByLine(filePath, typeString);
			hashSet.addAll(readFileByLine);
		}
		System.err.println(hashSet + "===" + hashSet.size());
	}

	public static Set<Integer> readFileByLine(String filePath, String typeString) {
		BufferedReader reader = null;
		List<String> strList = new ArrayList<>();
		Set<Integer> hashSet = new HashSet<>();
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
			String tempString = null;
			int line = 1;


//			String typeString = "起诉";
//			String typeString = "投资";
//			String typeString = "质押";
//			String typeString = "高管减持";
//			String typeString = "股份股权转让";
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
//				System.out.println("line " + line + ": " + tempString);
//				System.out.println("line " + line + ": ");
//				Set<String> jsonString = AppFileCheck.getJsonString(tempString, line);
//				Set<String> jsonString = AppFileCheck.getJsonString(tempString, line, typeString);
//				boolean flag = AppFileCheck.getJsonStringToBoolean(tempString, line, typeString);
				Set<Integer> jsonStringToNum = AppFileCheckOrder.getJsonStringToNum(tempString, line, typeString);
//				if (flag) {
//					System.out.println("#####################################################");
//				}
//				strList.add(tempString.trim());
				line++;
				hashSet.addAll(jsonStringToNum);
			}
//			System.err.println(hashSet);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
//		return strList;
		return hashSet;
	}

	public static List<String> readFileByLine(String filePath) {
		BufferedReader reader = null;
		List<String> strList = new ArrayList<>();
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
			String tempString = null;
			int line = 1;
//			Set<String> hashSet = new HashSet<>();

//			String typeString = "起诉";
//			String typeString = "投资";
//			String typeString = "质押";
//			String typeString = "高管减持";
//			String typeString = "股份股权转让";
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
//				System.out.println("line " + line + ": " + tempString);
//				System.out.println("line " + line + ": ");
//				Set<String> jsonString = AppFileCheck.getJsonString(tempString, line);
//				Set<String> jsonString = AppFileCheck.getJsonString(tempString, line, typeString);
//				boolean flag = AppFileCheckOrder.getJsonStringToBoolean(tempString, line, typeString);
//				if (flag) {
//					System.out.println("#####################################################");
//				}
				boolean blank = StringUtils.isBlank(tempString.trim());
				if (blank) {
					continue;
				} else {
					strList.add(tempString.trim());
				}
				line++;
//				hashSet.addAll(jsonString);
			}
//			System.err.println(hashSet);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return strList;
//		return null;
	}

	public static Set<String> getJsonString(String text, int lineNo) {
		Set<String> hashSet = new HashSet<>();
		Object parse = JSONObject.parse(text);
		JSONObject jsonObject = (JSONObject) parse;
		String content = (String) jsonObject.get("content");
		Object object = jsonObject.get("events");
		JSONArray jsonArray = (JSONArray) object;
		for (Object object2 : jsonArray) {
			JSONObject jsonObject2 = (JSONObject) object2;
			Object object3 = jsonObject2.get("mentions");
			Object typeObject = jsonObject2.get("type");
			hashSet.add(typeObject.toString());
//			System.out.println("typeObject===" + typeObject);
			JSONArray jsonArray3 = (JSONArray) object3;
			for (Object object4 : jsonArray3) {
				JSONObject jsonObject4 = (JSONObject) object4;
				String wordString = (String) jsonObject4.get("word");

				String newWordString = getNewStr(wordString);

				Object object5 = jsonObject4.get("span");
				if (Objects.isNull(object5)) {
					continue;
				}
//				System.out.print(object5 + "===");
				JSONArray jsonArray5 = (JSONArray) object5;
				Integer start = (Integer) jsonArray5.get(0);
				Integer end = (Integer) jsonArray5.get(1);
				try {
					String substring = content.substring(start, end);
					if (!newWordString.equals(getNewStr(substring))) {
//						System.err.println(lineNo + "======" + content);
						System.err.println(lineNo + "===span====" + object5 + "===substring===" + substring
								+ "===wordString===" + wordString);
					} else {
//						System.out.print(wordString);
					}

				} catch (Exception e) {
					System.err.println("lineNo######" + lineNo + "===start" + start + "===end===" + end + "===msg==="
							+ e.getMessage());
					// TODO: handle exception
				}

			}
		}
		System.out.println(hashSet);
//		System.out.println();
		return hashSet;
	}

	public static Set<String> getJsonString(String text, int lineNo, String type) {
		String nextLine = "\r\n";
		Set<String> hashSet = new HashSet<>();
		Object parse = JSONObject.parse(text);
		JSONObject jsonObject = (JSONObject) parse;
		String content = (String) jsonObject.get("content");
		Object object = jsonObject.get("events");
		JSONArray jsonArray = (JSONArray) object;
		for (Object object2 : jsonArray) {
			JSONObject jsonObject2 = (JSONObject) object2;
			Object object3 = jsonObject2.get("mentions");
			Object typeObject = jsonObject2.get("type");
//			hashSet.add(typeObject.toString());
//			System.out.println("typeObject===" + typeObject);

			if (type.equalsIgnoreCase(typeObject.toString())) {
				JSONArray jsonArray3 = (JSONArray) object3;
				for (Object object4 : jsonArray3) {
					JSONObject jsonObject4 = (JSONObject) object4;
					String wordString = (String) jsonObject4.get("word");
					String roleString = (String) jsonObject4.get("role");

					String outString = content + nextLine + roleString + nextLine + wordString;
					System.err.println(lineNo + "======" + outString);
					System.out.println("==================================");

					String newWordString = getNewStr(wordString);

					Object object5 = jsonObject4.get("span");
					if (Objects.isNull(object5)) {
						continue;
					}
//					System.out.print(object5 + "===");
					JSONArray jsonArray5 = (JSONArray) object5;
					Integer start = (Integer) jsonArray5.get(0);
					Integer end = (Integer) jsonArray5.get(1);
					try {
						String substring = content.substring(start, end);
						if (!newWordString.equals(getNewStr(substring))) {
//							System.err.println(lineNo + "======" + content);
							System.err.println(lineNo + "===span====" + object5 + "===substring===" + substring
									+ "===wordString===" + wordString);
						} else {
//							System.out.print(wordString);
						}

					} catch (Exception e) {
						System.err.println("lineNo######" + lineNo + "===start" + start + "===end===" + end
								+ "===msg===" + e.getMessage());
						// TODO: handle exception
					}

				}
			}

		}
//		System.out.println(hashSet);
//		System.out.println("#####################################################");
		return hashSet;
	}

	public static boolean getJsonStringToBoolean(String text, int lineNo, String type) {
		boolean flag = false;
		String nextLine = "\r\n";
		Set<String> hashSet = new HashSet<>();
		Object parse = JSONObject.parse(text);
		JSONObject jsonObject = (JSONObject) parse;
		String content = (String) jsonObject.get("content");
		Object object = jsonObject.get("events");
		JSONArray jsonArray = (JSONArray) object;
		for (Object object2 : jsonArray) {
			JSONObject jsonObject2 = (JSONObject) object2;
			Object object3 = jsonObject2.get("mentions");
			Object typeObject = jsonObject2.get("type");
//			hashSet.add(typeObject.toString());
//			System.out.println("typeObject===" + typeObject);

			if (type.equalsIgnoreCase(typeObject.toString())) {

				String outcontentString = content;
				System.err.println(lineNo + "======" + outcontentString);
				flag = true;
				JSONArray jsonArray3 = (JSONArray) object3;
				for (Object object4 : jsonArray3) {
					JSONObject jsonObject4 = (JSONObject) object4;
					String wordString = (String) jsonObject4.get("word");
					String roleString = (String) jsonObject4.get("role");

//					String outString = content + nextLine + roleString + nextLine + wordString;
					String outString = roleString + nextLine + wordString;
					System.err.println(lineNo + "======" + outString);
//					System.out.println("==================================");

					String newWordString = getNewStr(wordString);

					Object object5 = jsonObject4.get("span");
					if (Objects.isNull(object5)) {
						continue;
					}
//					System.out.print(object5 + "===");
					JSONArray jsonArray5 = (JSONArray) object5;
					Integer start = (Integer) jsonArray5.get(0);
					Integer end = (Integer) jsonArray5.get(1);
					try {
						String substring = content.substring(start, end);
						if (!newWordString.equals(getNewStr(substring))) {
//							System.err.println(lineNo + "======" + content);
							System.err.println(lineNo + "===span====" + object5 + "===substring===" + substring
									+ "===wordString===" + wordString);
						} else {
//							System.out.print(wordString);
						}

					} catch (Exception e) {
						System.err.println("lineNo######" + lineNo + "===start" + start + "===end===" + end
								+ "===msg===" + e.getMessage());
						// TODO: handle exception
					}

				}
			}

		}
//		System.out.println(hashSet);
//		System.out.println("#####################################################");
		return flag;
	}

	public static Set<Integer> getJsonStringToNum(String text, int lineNo, String type) {
		boolean flag = false;
		String nextLine = "\r\n";
		Set<Integer> hashSet = new HashSet<>();
		Object parse = JSONObject.parse(text);
		JSONObject jsonObject = (JSONObject) parse;
		String content = (String) jsonObject.get("content");
		Object object = jsonObject.get("events");
		JSONArray jsonArray = (JSONArray) object;
		for (Object object2 : jsonArray) {
			JSONObject jsonObject2 = (JSONObject) object2;
			Object object3 = jsonObject2.get("mentions");
			Object typeObject = jsonObject2.get("type");
//			hashSet.add(typeObject.toString());
//			System.out.println("typeObject===" + typeObject);
			if (type.equalsIgnoreCase(typeObject.toString())) {
				hashSet.add(lineNo);
				String outcontentString = content;
				System.err.println(lineNo + "======" + outcontentString);
				flag = true;
				JSONArray jsonArray3 = (JSONArray) object3;
				for (Object object4 : jsonArray3) {
					JSONObject jsonObject4 = (JSONObject) object4;
					String wordString = (String) jsonObject4.get("word");
					String roleString = (String) jsonObject4.get("role");

//					String outString = content + nextLine + roleString + nextLine + wordString;
					String outString = roleString + nextLine + wordString;
					System.err.println(lineNo + "======" + outString);
//					System.out.println("==================================");

					String newWordString = getNewStr(wordString);

					Object object5 = jsonObject4.get("span");
					if (Objects.isNull(object5)) {
						continue;
					}
//					System.out.print(object5 + "===");
					JSONArray jsonArray5 = (JSONArray) object5;
					Integer start = (Integer) jsonArray5.get(0);
					Integer end = (Integer) jsonArray5.get(1);
					try {
						String substring = content.substring(start, end);
						if (!newWordString.equals(getNewStr(substring))) {
//							System.err.println(lineNo + "======" + content);
							System.err.println(lineNo + "===span====" + object5 + "===substring===" + substring
									+ "===wordString===" + wordString);
						} else {
//							System.out.print(wordString);
						}

					} catch (Exception e) {
						System.err.println("lineNo######" + lineNo + "===start" + start + "===end===" + end
								+ "===msg===" + e.getMessage());
						// TODO: handle exception
					}

				}
			}

		}
//		System.out.println(hashSet);
//		System.out.println("#####################################################");
		return hashSet;
	}

	private static String getNewStr(String subString) {
		if (subString.contains(";")) {
			subString = subString.replace(";", "~");
		}
		if (subString.contains("\\s+")) {
			subString = subString.replaceAll("\\s+", "");
		}
		if (subString.contains("；")) {
			subString = subString.replace("；", ",");
		}
		if (subString.contains("：")) {
			subString = subString.replace("：", ":");
		}
		if (subString.contains("、")) {
			subString = subString.replace("、", ",");
		}
		if (subString.contains("，")) {
			subString = subString.replace("，", ",");
		}
		if (subString.contains("。")) {
			subString = subString.replace("。", "");
		}
		if (subString.contains("（")) {
			subString = subString.replace("（", "(");
		}
		if (subString.contains("）")) {
			subString = subString.replace("）", ")");
		}
		if (subString.contains("。")) {
			subString = subString.replace("。", "");
		}
		if (subString.contains("？")) {
			subString = subString.replace("？", "?");
		}
		if (subString.contains(" ")) {
			subString = subString.trim();
		}
		if (subString.contains("\r\n")) {
			subString = subString.replace("\r\n", " ");
		}
		return subString;
	}
}

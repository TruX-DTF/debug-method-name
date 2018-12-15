package edu.lu.uni.serval.utils;

public class ReturnType {
	
	public enum ReturnTypeClassification {
		ALL,      // All return types.
		ALL_A_C,  // Abstract Exception, Arrays, Collection, Map and Number types.
		BASIC,    // Abstract return types as OTHERS except basic types.
		ABSTRACT, // Abstract all types into 12 types.
	}
	
//	private static Logger log = LoggerFactory.getLogger(ReturnType.class);
	
	public static final String VOID = "void";
	public static final String BOOLEAN = "boolean";
	public static final String INTEGER = "integer";
	public static final String OTHER = "other";
	
	public String readReturnType(String line, ReturnTypeClassification classification) {
		String returnType = readType(line);
		switch (classification) {
		case ALL:
			return this.readAllReturnTypes(returnType);
		case ALL_A_C:
			return this.readAllReturnTypes_(returnType);
		case BASIC:
			return this.readBasicReturnTypes(returnType);
		case ABSTRACT:
			return this.readAbstractReturnType(returnType);
		default:
			return "";
		}
	}
	
	/**
	 * Read the return type of each method.
	 * 
	 * @param line, 
	 * @return
	 */
	private String readAbstractReturnType(String returnType) {
		if ("void".equals(returnType)) {
			returnType = VOID;
		} else if ("boolean".equals(returnType) || "Boolean".equals(returnType)) {
			returnType = BOOLEAN;
		} else if ("Integer".equals(returnType) || "int".equals(returnType)) {
			returnType = INTEGER;
		} else if ("short".equals(returnType) || "Short".equals(returnType)) {
			returnType = INTEGER;
		} else if ("long".equals(returnType) || "Long".equals(returnType)) {
			returnType = INTEGER;
		} else if ("byte".equals(returnType) || "Byte".equals(returnType)) {
			returnType = INTEGER;
		} else if ("float".equals(returnType) || "Float".equals(returnType)) {
			returnType = "double";
		} else if ("double".equals(returnType) || "Double".equals(returnType)) {
			returnType = "double";
		} else if ("char".equals(returnType) || "Character".equals(returnType)) {
			returnType = INTEGER;
		} else if ("String".equals(returnType)) {
			returnType = "String";
		} else if ("Object".equals(returnType)) {
			returnType = "Object";
		} else if ("Class".equals(returnType)) {
			returnType = "Class";
		} else if (returnType.endsWith("Exception")) {
			returnType = "Exception";
		} else if (returnType.contains("[]")) {
			returnType = "Arrays";
		} else {
			if (returnType.endsWith("List")) {
				returnType = "Collection";
//				if ("ArrayList".equals(returnType) || "LinkedList".equals(returnType)) {
//					returnType = "Collection";					
//				}
			} else if (returnType.endsWith("Map")) {
				returnType = "Map";
//				if ("HashMap".equals(returnType) || "LinkedHashMap".equals(returnType) || "SortedMap".equals(returnType)
//						|| "TreeMap".equals(returnType) || "WeakHashMap".equals(returnType) || "IdentityHashMap".equals(returnType) || "EnumMap".equals(returnType)) {
//					returnType = "Map";
//				}
			} else if (returnType.endsWith("Set")) {
				returnType = "Collection";
//				if ("HashSet".equals(returnType) || "LinkedHashSet".equals(returnType) || "SortedSet".equals(returnType)
//						|| "TreeSet".equals(returnType) || "EnumSet".equals(returnType)) {
//					returnType = "Collection";
//				}
			} else if (returnType.endsWith("Queue") || returnType.endsWith("Deque")) {
				returnType = "Collection";
//				if ("PriorityQueue".equals(returnType) || "Deque".equals(returnType) || "ArrayDeque".equals(returnType)) {
//					returnType = "Collection";
//				}
			} else if (returnType.endsWith("Vector") || returnType.equals("Stack")) {
				returnType = "Collection";
			} else if (returnType.endsWith("Hashtable") || returnType.equals("Properties")) {
				returnType = "Map";
			} else if (returnType.endsWith("Iterator")) {
				returnType = "Iterator";
			} else {
				returnType = "OTHERS";
			}
		}
		return returnType;
	}

	/**
	 * Read all return type of each method.
	 * @param line, 
	 * @return
	 */
	private String readAllReturnTypes(String returnType) {
		if ("void".equals(returnType)) {
			returnType = VOID;
		} else if ("boolean".equals(returnType) || "Boolean".equals(returnType)) {
			returnType = BOOLEAN;
		} else if ("Integer".equals(returnType) || "int".equals(returnType)) {
			returnType = INTEGER;
		} else if ("short".equals(returnType) || "Short".equals(returnType)) {
			returnType = "short";
		} else if ("long".equals(returnType) || "Long".equals(returnType)) {
			returnType = "long";
		} else if ("byte".equals(returnType) || "Byte".equals(returnType)) {
			returnType = "byte";
		} else if ("float".equals(returnType) || "Float".equals(returnType)) {
			returnType = "float";
		} else if ("double".equals(returnType) || "Double".equals(returnType)) {
			returnType = "double";
		} else if ("char".equals(returnType) || "Character".equals(returnType)) {
			returnType = "char";
//		} else if (returnType.endsWith("Exception")) {
//			returnType = "Exception";
//		} else if (returnType.contains("[]")) {
//			returnType = "Arrays";
		}
		return returnType;
	}
	
	/**
	 * Read the return type of each method. 
	 * 
	 * Abstract Exception, Arrays, Collection, Map and Number types.
	 * 
	 * @param line, 
	 * @return
	 */
	 private String readAllReturnTypes_(String returnType) {
		if ("void".equals(returnType)) {
			returnType = VOID;
		} else if ("boolean".equals(returnType) || "Boolean".equals(returnType)) {
			returnType = BOOLEAN;
		} else if ("Integer".equals(returnType) || "int".equals(returnType)) {
			returnType = INTEGER;
		} else if ("short".equals(returnType) || "Short".equals(returnType)) {
			returnType = INTEGER;
		} else if ("long".equals(returnType) || "Long".equals(returnType)) {
			returnType = INTEGER;
		} else if ("byte".equals(returnType) || "Byte".equals(returnType)) {
			returnType = INTEGER;
		} else if ("float".equals(returnType) || "Float".equals(returnType)) {
			returnType = "double";
		} else if ("double".equals(returnType) || "Double".equals(returnType)) {
			returnType = "double";
		} else if ("char".equals(returnType) || "Character".equals(returnType)) {
			returnType = "char";
//		} else if (returnType.endsWith("Exception")) {
//			returnType = "Exception";
		} else if (returnType.contains("[]")) {
			returnType = "Arrays";
		} else {
			if (returnType.endsWith("List")) {
				returnType = "Collection";	
//				if ("ArrayList".equals(returnType) || "LinkedList".equals(returnType)) {
//					returnType = "Collection";					
//				}
			} else if (returnType.endsWith("Map")) {
				returnType = "Map";
//				if ("HashMap".equals(returnType) || "LinkedHashMap".equals(returnType) || "SortedMap".equals(returnType)
//						|| "TreeMap".equals(returnType) || "WeakHashMap".equals(returnType) || "IdentityHashMap".equals(returnType) || "EnumMap".equals(returnType)) {
//					returnType = "Map";
//				}
			} else if (returnType.endsWith("Set")) {
				returnType = "Collection";
//				if ("HashSet".equals(returnType) || "LinkedHashSet".equals(returnType) || "SortedSet".equals(returnType)
//						|| "TreeSet".equals(returnType) || "EnumSet".equals(returnType)) {
//					returnType = "Collection";
//				}
			} else if (returnType.endsWith("Queue") || returnType.endsWith("Deque")) {
				returnType = "Collection";
//				if ("PriorityQueue".equals(returnType) || "Deque".equals(returnType) || "ArrayDeque".equals(returnType)) {
//					returnType = "Collection";
//				}
			} else if (returnType.endsWith("Vector") || returnType.equals("Stack")) {
				returnType = "Collection";
			} else if (returnType.endsWith("Hashtable") || returnType.equals("Properties")) {
				returnType = "Map";
			} else if (returnType.endsWith("Iterator")) {
				returnType = "Iterator";
			}
		}
		return returnType;
	}
	
	/**
	 * Read the return type of each method.
	 * Abstract return types as OTHERS except basic types.
	 * @param line, 
	 * @return
	 */
	 private String readBasicReturnTypes(String returnType) {
		if ("void".equals(returnType)) {
			returnType = VOID;
		} else if ("boolean".equals(returnType) || "Boolean".equals(returnType)) {
			returnType = BOOLEAN;
		} else if ("Integer".equals(returnType) || "int".equals(returnType)) {
			returnType = INTEGER;
		} else if ("short".equals(returnType) || "Short".equals(returnType)) {
			returnType = "short"; 
		} else if ("long".equals(returnType) || "Long".equals(returnType)) {
			returnType = "long";
		} else if ("byte".equals(returnType) || "Byte".equals(returnType)) {
			returnType = "byte";
		} else if ("float".equals(returnType) || "Float".equals(returnType)) {
			returnType = "float";
		} else if ("double".equals(returnType) || "Double".equals(returnType)) {
			returnType = "double";
		} else if ("char".equals(returnType) || "Character".equals(returnType)) {
			returnType = "char";
		} else if ("String".equals(returnType)) {
			returnType = "String";
		} else if ("Object".equals(returnType)) {
			returnType = "Object";
		} else if ("Class".equals(returnType)) {
			returnType = "Class";
		} else if (returnType.endsWith("Exception")) {
			returnType = "Exception";
		} else if (returnType.contains("[]")) {
			returnType = "Arrays";
		} else {
			if (returnType.endsWith("List")) {
//				if ("ArrayList".equals(returnType) || "LinkedList".equals(returnType)) {
//					returnType = "List";
//				}
				returnType = "List";
			} else if (returnType.endsWith("Map")) {
//				if ("HashMap".equals(returnType) || "LinkedHashMap".equals(returnType) || "SortedMap".equals(returnType)
//						|| "TreeMap".equals(returnType) || "WeakHashMap".equals(returnType) || "IdentityHashMap".equals(returnType) || "EnumMap".equals(returnType)) {
//					returnType = "Map";
//				}
				returnType = "Map";
			} else if (returnType.endsWith("Set")) {
//				if ("HashSet".equals(returnType) || "LinkedHashSet".equals(returnType) || "SortedSet".equals(returnType)
//						|| "TreeSet".equals(returnType) || "EnumSet".equals(returnType)) {
//					returnType = "Set";
//				}
				returnType = "Set";
			} else if (returnType.endsWith("Queue") || returnType.endsWith("Deque")) {
//				if ("PriorityQueue".equals(returnType) || "Deque".equals(returnType) || "ArrayDeque".equals(returnType)) {
//					returnType = "Queue";
//				}
				returnType = "Queue";
			} else if (returnType.endsWith("Vector") || returnType.equals("Stack")) {
				returnType = "List";
			} else if (returnType.endsWith("Hashtable") || returnType.equals("Properties")) {
				returnType = "Map";
			} else if (returnType.endsWith("Iterator")) {
				returnType = "Iterator";
			} else {
				returnType = "OTHERS";
			}
		}
		return returnType;
	}
	
	public String readType(String returnType) {
		int indexOfSharp = returnType.indexOf("#");
		if (indexOfSharp >= 0) {
			String key = returnType.substring(0, indexOfSharp);
			returnType = key.substring(key.lastIndexOf(":") + 1);
		}
		
		if (returnType.endsWith("[]")) return "Arrays[]";
		
		int index = returnType.indexOf("<");
		if (index != -1) {
			if (index == 0) {
//				log.error("######:" + returnType);
				while (index == 0) {
					returnType = returnType.substring(returnType.indexOf(">") + 1).trim();
					index = returnType.indexOf(">");
				}
				index = returnType.indexOf("<");
				if (index == -1) index = returnType.length();
			}
			returnType = returnType.substring(0, index);
		}
		index = returnType.lastIndexOf(".");
		if (index != -1) { // && returnType.startsWith("java.")) {
//			log.error("###:" + returnType);
			returnType = returnType.substring(index + 1);
		}

		return returnType;
	}
	
//	public String readType2(String returnType) {
//		int index = returnType.indexOf("<");
//		if (index != -1) {
//			if (index == 0) {
//				log.error("######:" + returnType);
//				while (index == 0) {
//					returnType = returnType.substring(returnType.indexOf(">") + 1).trim();
//					index = returnType.indexOf(">");
//				}
//				index = returnType.indexOf("<");
//				if (index == -1) index = returnType.length();
//			}
//			returnType = returnType.substring(0, index);
//		}
//
//		return returnType;
//	}
	
//	/**
//	 * Read all return type of each method.
//	 * Only abstract arrays return type and Exception return type.
//	 * @param line, 
//	 * @return
//	 */
//	public String readAllReturnTypes2(String returnType) {
//		if (returnType.endsWith("[]")) return "Arrays[]";
//		
//		int index = returnType.indexOf("<");
//		if (index != -1) {
//			if (index == 0) {
//				log.error("######:" + returnType);
//				while (index == 0) {
//					returnType = returnType.substring(returnType.indexOf(">") + 1).trim();
//					index = returnType.indexOf(">");
//				}
//				index = returnType.indexOf("<");
//				if (index == -1)
//					index = returnType.length();
//			}
//			returnType = returnType.substring(0, index);
//		}
//		index = returnType.lastIndexOf(".");
//		if (index != -1) { // && returnType.startsWith("java.")) {
//			log.error("###:" + returnType);
//			returnType = returnType.substring(index + 1);
//		}
//
//		if ("void".equals(returnType)) {
//			returnType = VOID;
//		} else if ("boolean".equals(returnType) || "Boolean".equals(returnType)) {
//			returnType = BOOLEAN;
//		} else if ("Integer".equals(returnType) || "int".equals(returnType)) {
//			returnType = INTEGER;
//		} else if ("short".equals(returnType) || "Short".equals(returnType)) {
//			returnType = "short"; 
//		} else if ("long".equals(returnType) || "Long".equals(returnType)) {
//			returnType = "long";
//		} else if ("byte".equals(returnType) || "Byte".equals(returnType)) {
//			returnType = "byte";
//		} else if ("float".equals(returnType) || "Float".equals(returnType)) {
//			returnType = "float";
//		} else if ("double".equals(returnType) || "Double".equals(returnType)) {
//			returnType = "double";
////		} else if (returnType.endsWith("Exception")) {
////			returnType = "Exception";
//		} else if (returnType.contains("[]")) {
//			returnType = "Arrays[]";
//		}
//		return returnType;
//	}
}

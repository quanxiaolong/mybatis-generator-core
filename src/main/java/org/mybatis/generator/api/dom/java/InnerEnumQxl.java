/*
 *  Copyright 2006 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.mybatis.generator.api.dom.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mybatis.generator.api.dom.OutputUtilities;

/**
 * This class encapsulates the idea of an inner enum - it has methods that make
 * it easy to generate inner enum.
 * 
 * @author Jeff Butler
 */
public class InnerEnumQxl extends JavaElement {

    private List<Field> fields;

    private List<InnerClass> innerClasses;

    private List<InnerEnumQxl> innerEnums;

    private FullyQualifiedJavaType type;

    private Set<FullyQualifiedJavaType> superInterfaceTypes;

    private List<Method> methods;

    private Map<String,String> enumConstants;

    /**
     * 
     */
    public InnerEnumQxl(FullyQualifiedJavaType type) {
        super();
        this.type = type;
        fields = new ArrayList<Field>();
        innerClasses = new ArrayList<InnerClass>();
        innerEnums = new ArrayList<InnerEnumQxl>();
        superInterfaceTypes = new HashSet<FullyQualifiedJavaType>();
        methods = new ArrayList<Method>();
        enumConstants = new LinkedHashMap<String, String>();
    }

    /**
     * @return Returns the fields.
     */
    public List<Field> getFields() {
        return fields;
    }

    public void addField(Field field) {
        fields.add(field);
    }

    /**
     * @return Returns the innerClasses.
     */
    public List<InnerClass> getInnerClasses() {
        return innerClasses;
    }

    public void addInnerClass(InnerClass innerClass) {
        innerClasses.add(innerClass);
    }

    public List<InnerEnumQxl> getInnerEnums() {
        return innerEnums;
    }

    public void addInnerEnum(InnerEnumQxl innerEnum) {
        innerEnums.add(innerEnum);
    }

    public Map<String,String> getEnumConstants() {
        return enumConstants;
    }

    public void addEnumConstant(String dbKey,String attrKey) {
    	if(enumConstants.containsKey(dbKey))
    		return ;
        enumConstants.put(dbKey, attrKey);
    }

    public String getFormattedContent(int indentLevel) {
        StringBuilder sb = new StringBuilder();

        addFormattedJavadoc(sb, indentLevel);
        addFormattedAnnotations(sb, indentLevel);

        OutputUtilities.javaIndent(sb, indentLevel);
        if (getVisibility() == JavaVisibility.PUBLIC) {
            sb.append(getVisibility().getValue());
        }

        sb.append("enum "); //$NON-NLS-1$
        sb.append(getType().getShortName());

        if (superInterfaceTypes.size() > 0) {
            sb.append(" implements "); //$NON-NLS-1$

            boolean comma = false;
            for (FullyQualifiedJavaType fqjt : superInterfaceTypes) {
                if (comma) {
                    sb.append(", "); //$NON-NLS-1$
                } else {
                    comma = true;
                }

                sb.append(fqjt.getShortName());
            }
        }

        sb.append(" {"); //$NON-NLS-1$
        indentLevel++;

//        Iterator<Map.Entry<String, String>> iterator = enumConstants.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, String> entry = iterator.next();
//            String dbKey=entry.getKey();
//            String attrkey=entry.getValue();
//            OutputUtilities.newLine(sb);
//            OutputUtilities.javaIndent(sb, indentLevel);
//            String enumKey="FIELD_";
//            sb.append(enumKey);
//            sb.append(dbKey);
//            sb.append("(");
//            sb.append("\"");
//            sb.append(dbKey);
//            sb.append("\"");
//            sb.append(",");
//            sb.append("\"");
//            sb.append(attrkey);
//            sb.append("\"");
//            sb.append(")");
//            if (iterator.hasNext()) {
//                sb.append(',');
//            } else {
//                sb.append(';');
//            }
//        }

        if (fields.size() > 0) {
            OutputUtilities.newLine(sb);
        }
        //创建枚举
        Iterator<Field> fldIter = fields.iterator();
        while (fldIter.hasNext()) {
        	Field field = fldIter.next();
        	String dbKey=field.getDbName();
			String attrkey=field.getName();
			String remark=field.getRemark();
			OutputUtilities.newLine(sb);
			OutputUtilities.javaIndent(sb, indentLevel);
			String enumKey="FIELD_";
			sb.append(enumKey);
			sb.append(dbKey);
			sb.append("(");
			sb.append("\"");
			sb.append(dbKey);
			sb.append("\"");
			sb.append(",");
			sb.append("\"");
			sb.append(attrkey);
			sb.append("\"");
			sb.append(")");
			sb.append(',');
			sb.append("//");
			sb.append(remark);
			if(!fldIter.hasNext()){
				OutputUtilities.newLine(sb);
				OutputUtilities.javaIndent(sb, indentLevel);
			    sb.append(';');
			}
		}
        //创建属性
        OutputUtilities.newLine(sb);
        OutputUtilities.javaIndent(sb, indentLevel);
        sb.append("private String dbCode;");
        OutputUtilities.newLine(sb);
		OutputUtilities.javaIndent(sb, indentLevel);
		sb.append("private String attrCode;");
        //创建构造函数
        OutputUtilities.newLine(sb);
        OutputUtilities.javaIndent(sb, indentLevel);
        sb.append("private ");
		sb.append(getType().getShortName());
		sb.append(" (String dbCode,String attrCode){");
		OutputUtilities.newLine(sb);
		OutputUtilities.javaIndent(sb, indentLevel);
		OutputUtilities.javaIndent(sb, indentLevel);
		sb.append("this.dbCode = dbCode;");
		OutputUtilities.newLine(sb);
		OutputUtilities.javaIndent(sb, indentLevel);
		OutputUtilities.javaIndent(sb, indentLevel);
		sb.append("this.attrCode = attrCode;");
		OutputUtilities.newLine(sb);
		OutputUtilities.javaIndent(sb, indentLevel);
		sb.append("}");
		//创建ToString和getDb
		OutputUtilities.newLine(sb);
		OutputUtilities.javaIndent(sb, indentLevel);
		sb.append("@Override");
		OutputUtilities.newLine(sb);
		OutputUtilities.javaIndent(sb, indentLevel);
		sb.append("public String toString(){");
		OutputUtilities.newLine(sb);
		OutputUtilities.javaIndent(sb, indentLevel);
		OutputUtilities.javaIndent(sb, indentLevel);
		sb.append("return this.dbCode;");
		OutputUtilities.newLine(sb);
		OutputUtilities.javaIndent(sb, indentLevel);
		sb.append("}");
		OutputUtilities.newLine(sb);
		OutputUtilities.javaIndent(sb, indentLevel);
		sb.append("public String getAttrCode(){");
		OutputUtilities.newLine(sb);
		OutputUtilities.javaIndent(sb, indentLevel);
		OutputUtilities.javaIndent(sb, indentLevel);
		sb.append("return this.attrCode;");
		OutputUtilities.newLine(sb);
		OutputUtilities.javaIndent(sb, indentLevel);
		sb.append("}");
        
        if (methods.size() > 0) {
            OutputUtilities.newLine(sb);
        }

        Iterator<Method> mtdIter = methods.iterator();
        while (mtdIter.hasNext()) {
            OutputUtilities.newLine(sb);
            Method method = mtdIter.next();
            sb.append(method.getFormattedContent(indentLevel, false));
            if (mtdIter.hasNext()) {
                OutputUtilities.newLine(sb);
            }
        }

        if (innerClasses.size() > 0) {
            OutputUtilities.newLine(sb);
        }

        Iterator<InnerClass> icIter = innerClasses.iterator();
        while (icIter.hasNext()) {
            OutputUtilities.newLine(sb);
            InnerClass innerClass = icIter.next();
            sb.append(innerClass.getFormattedContent(indentLevel));
            if (icIter.hasNext()) {
                OutputUtilities.newLine(sb);
            }
        }

        if (innerEnums.size() > 0) {
            OutputUtilities.newLine(sb);
        }

        Iterator<InnerEnumQxl> ieIter = innerEnums.iterator();
        while (ieIter.hasNext()) {
            OutputUtilities.newLine(sb);
            InnerEnumQxl innerEnum = ieIter.next();
            sb.append(innerEnum.getFormattedContent(indentLevel));
            if (ieIter.hasNext()) {
                OutputUtilities.newLine(sb);
            }
        }

        indentLevel--;
        OutputUtilities.newLine(sb);
        OutputUtilities.javaIndent(sb, indentLevel);
        sb.append('}');

        return sb.toString();
    }

    /**
     * @return Returns the superInterfaces.
     */
    public Set<FullyQualifiedJavaType> getSuperInterfaceTypes() {
        return superInterfaceTypes;
    }

    public void addSuperInterface(FullyQualifiedJavaType superInterface) {
        superInterfaceTypes.add(superInterface);
    }

    /**
     * @return Returns the methods.
     */
    public List<Method> getMethods() {
        return methods;
    }

    public void addMethod(Method method) {
        methods.add(method);
    }

    /**
     * @return Returns the type.
     */
    public FullyQualifiedJavaType getType() {
        return type;
    }
}

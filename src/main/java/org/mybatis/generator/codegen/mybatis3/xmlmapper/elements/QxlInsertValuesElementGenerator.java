/*
 *  Copyright 2009 The Apache Software Foundation
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
package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class QxlInsertValuesElementGenerator extends
        AbstractXmlElementGenerator {

    public QxlInsertValuesElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("sql"); //$NON-NLS-1$

        answer
                .addAttribute(new Attribute(
                        "id", introspectedTable.getInsertValues())); //$NON-NLS-1$

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();
        answer.addElement((new TextElement("(")));
        List<IntrospectedColumn> listItem=introspectedTable
                .getAllColumns();
        int index=1;
        int size=listItem.size();
        for (IntrospectedColumn introspectedColumn : listItem) {
        	sb.setLength(0);
        	sb.append("	");
        	sb.append(MyBatis3FormattingUtilities
                    .qxlGetParameterClause(introspectedColumn));
            if(index<size)
            	sb.append(',');
            answer.addElement((new TextElement(sb.toString())));
            index++;
        }
        answer.addElement((new TextElement(")")));
        if (context.getPlugins()
                .sqlMapUpdateByPrimaryKeySelectiveElementGenerated(answer,
                        introspectedTable)) {
        	parentElement.addElement(new TextElement(""));
        	parentElement.addElement(new TextElement("<!-- 插入字段值 -->"));
            parentElement.addElement(answer);
        }
    }
}

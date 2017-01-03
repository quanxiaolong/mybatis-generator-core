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

import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class QxlCommonElementGenerator extends AbstractXmlElementGenerator {

    public QxlCommonElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
    	createSelectElement(parentElement);
    	createInsertElement(parentElement);
    	createUpdateElement(parentElement);
    	createDeleteElement(parentElement);
    }

	private void createDeleteElement(XmlElement parentElement) {
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<!-- =============删除区域=============== -->"));
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<delete id=\"delete\" parameterType=\"DynamicCondition\">"));
		parentElement.addElement(new TextElement("	DELETE FROM "));
		parentElement.addElement(new TextElement("		<include refid=\"tableName\" />"));
		parentElement.addElement(new TextElement("	<include refid=\"database.operate.api.common.mapper.base_condition\" />"));
		parentElement.addElement(new TextElement("</delete>"));
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<delete id=\"deleteById\" parameterType=\"java.lang.Long\">"));
		parentElement.addElement(new TextElement("	DELETE FROM "));
		parentElement.addElement(new TextElement("		<include refid=\"tableName\" />"));
		parentElement.addElement(new TextElement("	WHERE "));
		parentElement.addElement(new TextElement("		<include refid=\"pk\" /> = #{id,jdbcType=BIGINT}"));
		parentElement.addElement(new TextElement("</delete>"));
	}

	private void createUpdateElement(XmlElement parentElement) {
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<!-- =============更新区域=============== -->"));
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<update id=\"update\">"));
		parentElement.addElement(new TextElement("	UPDATE"));
		parentElement.addElement(new TextElement("	<include refid=\"tableName\" />"));
		parentElement.addElement(new TextElement("	<set>"));
		parentElement.addElement(new TextElement("		<include refid=\"updateFields\"/>"));
		parentElement.addElement(new TextElement("	</set>"));
		parentElement.addElement(new TextElement("	<include refid=\"database.operate.api.common.mapper.base_condition\" />"));
		parentElement.addElement(new TextElement("</update>"));
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<update id=\"updateWithBatch\">"));
		parentElement.addElement(new TextElement("	<foreach collection=\"models\" item=\"model\" index=\"index\" separator=\";\"> "));
		parentElement.addElement(new TextElement("		UPDATE"));
		parentElement.addElement(new TextElement("		<include refid=\"tableName\" />"));
		parentElement.addElement(new TextElement("		<set>"));
		parentElement.addElement(new TextElement("			<include refid=\"updateFields\"/>"));
		parentElement.addElement(new TextElement("		</set>"));
		parentElement.addElement(new TextElement("		WHERE"));
		parentElement.addElement(new TextElement("			<include refid=\"pk\" /> = #{model.id,jdbcType=BIGINT}"));
		parentElement.addElement(new TextElement("	</foreach>"));
		parentElement.addElement(new TextElement("</update>"));
	}

	private void createInsertElement(XmlElement parentElement) {
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<!-- =============插入区域=============== -->"));
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<insert id=\"insert\" keyProperty=\"model.id\" useGeneratedKeys=\"true\">"));
		parentElement.addElement(new TextElement("	INSERT INTO "));
		parentElement.addElement(new TextElement("	<include refid=\"tableName\" /> "));
		parentElement.addElement(new TextElement("	<include refid=\"insertKeys\" />"));
		parentElement.addElement(new TextElement("	VALUES "));
		parentElement.addElement(new TextElement("	<include refid=\"insertValues\"/>"));
		parentElement.addElement(new TextElement("</insert>"));
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<insert id=\"insertWithBatch\">"));
		parentElement.addElement(new TextElement("	INSERT INTO "));
		parentElement.addElement(new TextElement("	<include refid=\"tableName\" />	"));
		parentElement.addElement(new TextElement("	<include refid=\"insertKeys\" />"));
		parentElement.addElement(new TextElement("	VALUES"));
		parentElement.addElement(new TextElement("	<foreach collection=\"models\" item=\"model\" index=\"index\" separator=\",\">"));
		parentElement.addElement(new TextElement("		<if test=\"models[index] != null\">"));
		parentElement.addElement(new TextElement("			<include refid=\"insertValues\"/>"));
		parentElement.addElement(new TextElement("		</if>"));
		parentElement.addElement(new TextElement("	</foreach>"));
		parentElement.addElement(new TextElement("</insert>"));
	}

	private void createSelectElement(XmlElement parentElement) {
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<!-- =============查询区域=============== -->"));
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<select id=\"searchById\" resultMap=\"BaseResultMap\" parameterType=\"java.lang.Long\">"));
		parentElement.addElement(new TextElement("	SELECT "));
		parentElement.addElement(new TextElement("			<include refid=\"Base_Column_List\" />"));
		parentElement.addElement(new TextElement("	FROM "));
		parentElement.addElement(new TextElement("		<include refid=\"tableName\" />"));
		parentElement.addElement(new TextElement("	WHERE"));
		parentElement.addElement(new TextElement("		<include refid=\"pk\" /> = #{id,jdbcType=BIGINT}"));
		parentElement.addElement(new TextElement("</select>"));
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<select id=\"searchWithBatch\" resultMap=\"BaseResultMap\">"));
		parentElement.addElement(new TextElement("	SELECT "));
		parentElement.addElement(new TextElement("		<include refid=\"Base_Column_List\" />"));
		parentElement.addElement(new TextElement("	FROM "));
		parentElement.addElement(new TextElement("		<include refid=\"tableName\" />"));
		parentElement.addElement(new TextElement("	WHERE "));
		parentElement.addElement(new TextElement("	<include refid=\"pk\" /> IN"));
		parentElement.addElement(new TextElement("	<foreach collection=\"ids\" item=\"id\" open=\"(\" close=\")\" separator=\",\">"));
		parentElement.addElement(new TextElement("		#{id,jdbcType=BIGINT}"));
		parentElement.addElement(new TextElement("	</foreach>"));
		parentElement.addElement(new TextElement("</select>"));
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<select id=\"searchCount\" resultType=\"java.lang.Integer\" parameterType=\"DynamicCondition\">"));
		parentElement.addElement(new TextElement("	SELECT "));
		parentElement.addElement(new TextElement("		COUNT(*)"));
		parentElement.addElement(new TextElement("	FROM "));
		parentElement.addElement(new TextElement("		<include refid=\"tableName\" />"));
		parentElement.addElement(new TextElement("	<include refid=\"database.operate.api.common.mapper.base_condition\" />"));
		parentElement.addElement(new TextElement("</select>"));
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<select id=\"search\" resultMap=\"BaseResultMap\" parameterType=\"DynamicCondition\">"));
		parentElement.addElement(new TextElement("	SELECT "));
		parentElement.addElement(new TextElement("		<include refid=\"Base_Column_List\" />"));
		parentElement.addElement(new TextElement("	FROM "));
		parentElement.addElement(new TextElement("		<include refid=\"tableName\" />"));
		parentElement.addElement(new TextElement("	<include refid=\"database.operate.api.common.mapper.base_condition\" />"));
		parentElement.addElement(new TextElement("</select>"));
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<select id=\"searchOne\" resultMap=\"BaseResultMap\" parameterType=\"DynamicCondition\">"));
		parentElement.addElement(new TextElement("	SELECT "));
		parentElement.addElement(new TextElement("		<include refid=\"Base_Column_List\" />"));
		parentElement.addElement(new TextElement("	FROM "));
		parentElement.addElement(new TextElement("		<include refid=\"tableName\" />"));
		parentElement.addElement(new TextElement("	<include refid=\"database.operate.api.common.mapper.base_condition\" />"));
		parentElement.addElement(new TextElement("</select>"));
		parentElement.addElement(new TextElement(""));
		parentElement.addElement(new TextElement("<select id=\"page\" resultMap=\"BaseResultMap\">"));
		parentElement.addElement(new TextElement("	SELECT "));
		parentElement.addElement(new TextElement("		<include refid=\"Base_Column_List\" />"));
		parentElement.addElement(new TextElement("	FROM "));
		parentElement.addElement(new TextElement("		<include refid=\"tableName\" />"));
		parentElement.addElement(new TextElement("	<include refid=\"database.operate.api.common.mapper.base_condition\" />"));
		parentElement.addElement(new TextElement("</select>"));
	}
}

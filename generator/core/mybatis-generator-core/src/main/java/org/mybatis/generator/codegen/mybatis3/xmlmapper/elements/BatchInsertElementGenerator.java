package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

import java.util.ArrayList;
import java.util.List;

/**
 * 批量insert的xml组装
 *
 * @author yangfengshuai
 * @date 2020/3/12
 */
public class BatchInsertElementGenerator extends AbstractXmlElementGenerator {
    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("insert");
        List<String> valuesClauses = new ArrayList<String>();
        answer.addAttribute(new Attribute("id", introspectedTable.getBatchInsertStatementId()));
        FullyQualifiedJavaType parameterType = FullyQualifiedJavaType.getNewListInstance();
        answer.addAttribute(new Attribute("parameterType", parameterType.getFullyQualifiedName()));
        context.getCommentGenerator().addComment(answer);
        answer.addAttribute(new Attribute("useGeneratedKeys", "true"));
        answer.addAttribute(new Attribute("keyProperty", "id"));
        answer.addAttribute(new Attribute("keyColumn", "id"));
        StringBuilder insertClause = new StringBuilder();
        insertClause.append("insert into ");
        insertClause.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        insertClause.append(" (");
        StringBuilder valuesClause = new StringBuilder();
        valuesClause.append("values ");
        valuesClauses.add(valuesClause.toString());
        valuesClause.setLength(0);
        valuesClause.append("<foreach collection=\"list\"  item=\"item\" separator=\",\"> ");
        valuesClauses.add(valuesClause.toString());
        valuesClause.setLength(0);
        valuesClause.append("(");
        List<IntrospectedColumn> columns = ListUtilities.removeIdentityAndGeneratedAlwaysColumns(introspectedTable.getAllColumns());
        for (int i = 0; i < columns.size(); i++) {
            IntrospectedColumn introspectedColumn = columns.get(i);

            insertClause.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            valuesClause.append(MyBatis3FormattingUtilities.getBatchParameterClause("item", introspectedColumn));
            if (i + 1 < columns.size()) {
                insertClause.append(", ");
                valuesClause.append(", ");
            }
            if (valuesClause.length() > 80) {
                answer.addElement(new TextElement(insertClause.toString()));
                insertClause.setLength(0);
                OutputUtilities.xmlIndent(insertClause, 1);
                valuesClauses.add(valuesClause.toString());
                valuesClause.setLength(0);
                OutputUtilities.xmlIndent(valuesClause, 1);
            }
        }
        insertClause.append(')');
        answer.addElement(new TextElement(insertClause.toString()));

        valuesClause.append(')');
        valuesClauses.add(valuesClause.toString());
        valuesClause.setLength(0);
        valuesClause.append("</foreach>");
        valuesClauses.add(valuesClause.toString());
        for (String clause : valuesClauses) {
            answer.addElement(new TextElement(clause));
        }

        if (context.getPlugins().sqlMapInsertElementGenerated(answer, introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}

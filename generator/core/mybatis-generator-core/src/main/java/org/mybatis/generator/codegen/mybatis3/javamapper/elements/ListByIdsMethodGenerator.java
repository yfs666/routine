package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;

import java.util.Set;
import java.util.TreeSet;

/**
 * 根据id集合查询
 * @author yangfengshuai
 * @date 2020/3/12
 */
public class ListByIdsMethodGenerator extends AbstractJavaMapperMethodGenerator {


    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
        returnType.addTypeArgument(introspectedTable.getRules().calculateAllFieldsClass());
        FullyQualifiedJavaType paramList = FullyQualifiedJavaType.getNewListInstance();
        paramList.addTypeArgument(new FullyQualifiedJavaType("Integer"));
        method.setReturnType(returnType);
        importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
        importedTypes.add(introspectedTable.getRules().calculateAllFieldsClass());
        method.setName(introspectedTable.getListByIdsStatementId());
        importedTypes.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param"));
        method.addParameter(new Parameter(paramList, "ids","@Param(\"ids\")"));
        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(
                method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }
}

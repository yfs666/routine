/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.082+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object FieldsonlyDynamicSqlSupport {
    object Fieldsonly : SqlTable("FIELDSONLY") {
        val integerfield = column<Int>("INTEGERFIELD", JDBCType.INTEGER)

        val doublefield = column<Double>("DOUBLEFIELD", JDBCType.DOUBLE)

        val floatfield = column<Double>("FLOATFIELD", JDBCType.DOUBLE)
    }
}
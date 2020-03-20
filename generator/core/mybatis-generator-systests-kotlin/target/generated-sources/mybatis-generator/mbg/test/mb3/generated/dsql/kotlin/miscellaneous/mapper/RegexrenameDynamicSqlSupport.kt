/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.108+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object RegexrenameDynamicSqlSupport {
    object Regexrename : SqlTable("REGEXRENAME") {
        val id = column<Int>("CUST_ID", JDBCType.INTEGER)

        val name = column<String>("CUST_NAME", JDBCType.VARCHAR)

        val address = column<String>("CUST_ADDRESS", JDBCType.VARCHAR)

        val zipCode = column<String>("ZIP_CODE", JDBCType.CHAR)
    }
}
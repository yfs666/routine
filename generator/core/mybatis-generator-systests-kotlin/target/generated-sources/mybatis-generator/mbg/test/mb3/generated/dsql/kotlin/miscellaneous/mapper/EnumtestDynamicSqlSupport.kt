/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.108+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper

import java.sql.JDBCType
import mbg.test.mb3.common.TestEnum
import org.mybatis.dynamic.sql.SqlTable

object EnumtestDynamicSqlSupport {
    object Enumtest : SqlTable("ENUMTEST") {
        val id = column<Int>("ID", JDBCType.INTEGER)

        val name = column<TestEnum>("NAME", JDBCType.VARCHAR)
    }
}
/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.103+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper.mbgtest

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object TranslationDynamicSqlSupport {
    object Translation : SqlTable("MBGTEST.TRANSLATIONS") {
        val id = column<Int>("ID", JDBCType.INTEGER)

        val translation = column<String>("TRANSLATION", JDBCType.VARCHAR)
    }
}
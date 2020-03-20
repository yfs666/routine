/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.106+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper

import java.sql.JDBCType
import java.util.Date
import mbg.test.common.FirstName
import mbg.test.common.MyTime
import org.mybatis.dynamic.sql.SqlTable

object MyObjectDynamicSqlSupport {
    object MyObject : SqlTable("PKFIELDS") {
        val id2 = column<Int>("ID2", JDBCType.INTEGER)

        val id1 = column<Int>("ID1", JDBCType.INTEGER)

        val firstname = column<FirstName>("FIRSTNAME", JDBCType.VARCHAR, "mbg.test.mb3.common.FirstNameTypeHandler")

        val lastname = column<String>("LASTNAME", JDBCType.VARCHAR)

        val startDate = column<Date>("DATEFIELD", JDBCType.DATE)

        val timefield = column<MyTime>("TIMEFIELD", JDBCType.TIME, "mbg.test.mb3.common.MyTimeTypeHandler")

        val timestampfield = column<Date>("TIMESTAMPFIELD", JDBCType.TIMESTAMP)

        val decimal60field = column<Int>("DECIMAL60FIELD", JDBCType.DECIMAL)

        val decimal100field = column<Long>("DECIMAL100FIELD", JDBCType.DECIMAL)

        val decimal155field = column<Double>("DECIMAL155FIELD", JDBCType.DECIMAL)

        val wierdField = column<Int>("\"wierd\$Field\"", JDBCType.INTEGER)

        val birthDate = column<Date>("\"birth date\"", JDBCType.DATE)

        val stringboolean = column<String>("STRINGBOOLEAN", JDBCType.CHAR)
    }
}
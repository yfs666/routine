/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.104+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.readonly.mapper

import java.math.BigDecimal
import java.sql.JDBCType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import org.mybatis.dynamic.sql.SqlTable

object PkfieldsDynamicSqlSupport {
    object Pkfields : SqlTable("PKFIELDS") {
        val id2 = column<Int>("ID2", JDBCType.INTEGER)

        val id1 = column<Int>("ID1", JDBCType.INTEGER)

        val firstname = column<String>("FIRSTNAME", JDBCType.VARCHAR)

        val lastname = column<String>("LASTNAME", JDBCType.VARCHAR)

        val datefield = column<LocalDate>("DATEFIELD", JDBCType.DATE)

        val timefield = column<LocalTime>("TIMEFIELD", JDBCType.TIME)

        val timestampfield = column<LocalDateTime>("TIMESTAMPFIELD", JDBCType.TIMESTAMP)

        val decimal30field = column<Short>("DECIMAL30FIELD", JDBCType.DECIMAL)

        val decimal60field = column<Int>("DECIMAL60FIELD", JDBCType.DECIMAL)

        val decimal100field = column<Long>("DECIMAL100FIELD", JDBCType.DECIMAL)

        val decimal155field = column<BigDecimal>("DECIMAL155FIELD", JDBCType.DECIMAL)

        val wierdField = column<Int>("\"wierd\$Field\"", JDBCType.INTEGER)

        val birthDate = column<LocalDate>("\"birth date\"", JDBCType.DATE)

        val stringboolean = column<Boolean>("STRINGBOOLEAN", JDBCType.CHAR, "mbg.test.mb3.common.StringBooleanTypeHandler")
    }
}
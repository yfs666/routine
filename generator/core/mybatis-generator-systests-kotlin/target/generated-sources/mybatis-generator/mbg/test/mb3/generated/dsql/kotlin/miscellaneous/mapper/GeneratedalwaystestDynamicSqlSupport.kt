/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.109+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object GeneratedalwaystestDynamicSqlSupport {
    object Generatedalwaystest : SqlTable("GENERATEDALWAYSTEST") {
        val id = column<Int>("ID", JDBCType.INTEGER)

        val name = column<String>("NAME", JDBCType.VARCHAR)

        val idPlus1 = column<Int>("ID_PLUS1", JDBCType.INTEGER)

        val idPlus2 = column<Int>("ID_PLUS2", JDBCType.INTEGER)

        val blob1 = column<ByteArray>("BLOB1", JDBCType.VARBINARY)
    }
}
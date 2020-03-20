/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.1+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object PkfieldsblobsDynamicSqlSupport {
    object Pkfieldsblobs : SqlTable("PKFIELDSBLOBS") {
        val id1 = column<Int>("ID1", JDBCType.INTEGER)

        val id2 = column<Int>("ID2", JDBCType.INTEGER)

        val firstname = column<String>("FIRSTNAME", JDBCType.VARCHAR)

        val lastname = column<String>("LASTNAME", JDBCType.VARCHAR)

        val blob1 = column<ByteArray>("BLOB1", JDBCType.VARBINARY)
    }
}
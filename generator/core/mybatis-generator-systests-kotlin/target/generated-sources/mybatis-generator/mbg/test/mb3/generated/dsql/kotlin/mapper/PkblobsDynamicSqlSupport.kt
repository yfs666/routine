/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.099+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object PkblobsDynamicSqlSupport {
    object Pkblobs : SqlTable("PKBLOBS") {
        val id = column<Int>("ID", JDBCType.INTEGER)

        val blob1 = column<ByteArray>("BLOB1", JDBCType.VARBINARY)

        val blob2 = column<ByteArray>("BLOB2", JDBCType.VARBINARY)

        val characterlob = column<String>("CHARACTERLOB", JDBCType.CLOB)
    }
}
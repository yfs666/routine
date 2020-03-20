/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.1+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object FieldsblobsDynamicSqlSupport {
    object Fieldsblobs : SqlTable("FIELDSBLOBS") {
        val firstname = column<String>("FIRSTNAME", JDBCType.VARCHAR)

        val lastname = column<String>("LASTNAME", JDBCType.VARCHAR)

        val blob1 = column<ByteArray>("BLOB1", JDBCType.VARBINARY)

        val blob2 = column<ByteArray>("BLOB2", JDBCType.VARBINARY)

        val blob3 = column<ByteArray>("BLOB3", JDBCType.BINARY)
    }
}
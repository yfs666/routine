/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.105+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.readonly.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object AwfulTableDynamicSqlSupport {
    object AwfulTable : SqlTable("\"awful table\"") {
        val customerId = column<Int>("\"CuStOmEr iD\"", JDBCType.INTEGER)

        val firstFirstName = column<String>("\"first name\"", JDBCType.VARCHAR)

        val secondFirstName = column<String>("FIRST_NAME", JDBCType.VARCHAR)

        val thirdFirstName = column<String>("FIRSTNAME", JDBCType.VARCHAR)

        val lastName = column<String>("\"last name\"", JDBCType.VARCHAR)

        val eMail = column<String>("E_MAIL", JDBCType.VARCHAR)

        val id1 = column<Int>("\"_id1\"", JDBCType.INTEGER)

        val id2 = column<Int>("\"\$id2\"", JDBCType.INTEGER)

        val id5 = column<Int>("\"id5_\"", JDBCType.INTEGER)

        val id6 = column<Int>("\"id6\$\"", JDBCType.INTEGER)

        val id7 = column<Int>("\"id7\$\$\"", JDBCType.INTEGER)

        val emailaddress = column<String>("EMAILADDRESS", JDBCType.VARCHAR)

        val from = column<String>("\"from\"", JDBCType.VARCHAR)

        val active = column<Boolean>("ACTIVE", JDBCType.BIT)

        val active1 = column<Boolean>("ACTIVE1", JDBCType.BOOLEAN)

        val active2 = column<ByteArray>("ACTIVE2", JDBCType.BIT)

        val dbClass = column<String>("CLASS", JDBCType.VARCHAR)
    }
}
/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.097+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object PkonlyDynamicSqlSupport {
    object Pkonly : SqlTable("PKONLY") {
        val id = column<Int>("ID", JDBCType.INTEGER)

        val seqNum = column<Int>("SEQ_NUM", JDBCType.INTEGER)
    }
}
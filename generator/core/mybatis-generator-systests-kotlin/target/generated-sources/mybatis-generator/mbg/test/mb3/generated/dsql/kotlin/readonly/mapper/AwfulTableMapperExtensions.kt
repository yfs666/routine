/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.106+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.readonly.mapper

import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.active
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.active1
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.active2
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.customerId
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.dbClass
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.eMail
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.emailaddress
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.firstFirstName
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.from
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.id1
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.id2
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.id5
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.id6
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.id7
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.lastName
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.secondFirstName
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.AwfulTableDynamicSqlSupport.AwfulTable.thirdFirstName
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun AwfulTableMapper.count(completer: CountCompleter) =
    countFrom(this::count, AwfulTable, completer)

private val columnList = listOf(customerId, firstFirstName, secondFirstName, thirdFirstName, lastName, eMail, id1, id2, id5, id6, id7, emailaddress, from, active, active1, active2, dbClass)

fun AwfulTableMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, AwfulTable, completer)

fun AwfulTableMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, AwfulTable, completer)

fun AwfulTableMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, AwfulTable, completer)

fun AwfulTableMapper.selectByPrimaryKey(customerId_: Int) =
    selectOne {
        where(customerId, isEqualTo(customerId_))
    }
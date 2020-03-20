/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.103+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.active
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.active1
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.active2
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.customerId
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.dbClass
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.eMail
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.emailaddress
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.firstFirstName
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.from
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.id1
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.id2
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.id5
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.id6
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.id7
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.lastName
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.secondFirstName
import mbg.test.mb3.generated.dsql.kotlin.mapper.AwfulTableDynamicSqlSupport.AwfulTable.thirdFirstName
import mbg.test.mb3.generated.dsql.kotlin.model.AwfulTableRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun AwfulTableMapper.count(completer: CountCompleter) =
    countFrom(this::count, AwfulTable, completer)

fun AwfulTableMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, AwfulTable, completer)

fun AwfulTableMapper.deleteByPrimaryKey(customerId_: Int) =
    delete {
        where(customerId, isEqualTo(customerId_))
    }

fun AwfulTableMapper.insertMultipleHelper(multipleInsertStatement: MultiRowInsertStatementProvider<AwfulTableRecord>) =
    insertMultiple(multipleInsertStatement.insertStatement, multipleInsertStatement.records)

fun AwfulTableMapper.insert(record: AwfulTableRecord) =
    insert(this::insert, record, AwfulTable) {
        map(firstFirstName).toProperty("firstFirstName")
        map(secondFirstName).toProperty("secondFirstName")
        map(thirdFirstName).toProperty("thirdFirstName")
        map(lastName).toProperty("lastName")
        map(eMail).toProperty("eMail")
        map(id1).toProperty("id1")
        map(id2).toProperty("id2")
        map(id5).toProperty("id5")
        map(id6).toProperty("id6")
        map(id7).toProperty("id7")
        map(emailaddress).toProperty("emailaddress")
        map(from).toProperty("from")
        map(active).toProperty("active")
        map(active1).toProperty("active1")
        map(active2).toProperty("active2")
        map(dbClass).toProperty("dbClass")
    }

fun AwfulTableMapper.insertMultiple(records: Collection<AwfulTableRecord>) =
    insertMultiple(this::insertMultipleHelper, records, AwfulTable) {
        map(firstFirstName).toProperty("firstFirstName")
        map(secondFirstName).toProperty("secondFirstName")
        map(thirdFirstName).toProperty("thirdFirstName")
        map(lastName).toProperty("lastName")
        map(eMail).toProperty("eMail")
        map(id1).toProperty("id1")
        map(id2).toProperty("id2")
        map(id5).toProperty("id5")
        map(id6).toProperty("id6")
        map(id7).toProperty("id7")
        map(emailaddress).toProperty("emailaddress")
        map(from).toProperty("from")
        map(active).toProperty("active")
        map(active1).toProperty("active1")
        map(active2).toProperty("active2")
        map(dbClass).toProperty("dbClass")
    }

fun AwfulTableMapper.insertMultiple(vararg records: AwfulTableRecord) =
    insertMultiple(records.toList())

fun AwfulTableMapper.insertSelective(record: AwfulTableRecord) =
    insert(this::insert, record, AwfulTable) {
        map(firstFirstName).toPropertyWhenPresent("firstFirstName", record::firstFirstName)
        map(secondFirstName).toPropertyWhenPresent("secondFirstName", record::secondFirstName)
        map(thirdFirstName).toPropertyWhenPresent("thirdFirstName", record::thirdFirstName)
        map(lastName).toPropertyWhenPresent("lastName", record::lastName)
        map(eMail).toPropertyWhenPresent("eMail", record::eMail)
        map(id1).toPropertyWhenPresent("id1", record::id1)
        map(id2).toPropertyWhenPresent("id2", record::id2)
        map(id5).toPropertyWhenPresent("id5", record::id5)
        map(id6).toPropertyWhenPresent("id6", record::id6)
        map(id7).toPropertyWhenPresent("id7", record::id7)
        map(emailaddress).toPropertyWhenPresent("emailaddress", record::emailaddress)
        map(from).toPropertyWhenPresent("from", record::from)
        map(active).toPropertyWhenPresent("active", record::active)
        map(active1).toPropertyWhenPresent("active1", record::active1)
        map(active2).toPropertyWhenPresent("active2", record::active2)
        map(dbClass).toPropertyWhenPresent("dbClass", record::dbClass)
    }

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

fun AwfulTableMapper.update(completer: UpdateCompleter) =
    update(this::update, AwfulTable, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: AwfulTableRecord) =
    apply {
        set(firstFirstName).equalTo(record::firstFirstName)
        set(secondFirstName).equalTo(record::secondFirstName)
        set(thirdFirstName).equalTo(record::thirdFirstName)
        set(lastName).equalTo(record::lastName)
        set(eMail).equalTo(record::eMail)
        set(id1).equalTo(record::id1)
        set(id2).equalTo(record::id2)
        set(id5).equalTo(record::id5)
        set(id6).equalTo(record::id6)
        set(id7).equalTo(record::id7)
        set(emailaddress).equalTo(record::emailaddress)
        set(from).equalTo(record::from)
        set(active).equalTo(record::active)
        set(active1).equalTo(record::active1)
        set(active2).equalTo(record::active2)
        set(dbClass).equalTo(record::dbClass)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: AwfulTableRecord) =
    apply {
        set(firstFirstName).equalToWhenPresent(record::firstFirstName)
        set(secondFirstName).equalToWhenPresent(record::secondFirstName)
        set(thirdFirstName).equalToWhenPresent(record::thirdFirstName)
        set(lastName).equalToWhenPresent(record::lastName)
        set(eMail).equalToWhenPresent(record::eMail)
        set(id1).equalToWhenPresent(record::id1)
        set(id2).equalToWhenPresent(record::id2)
        set(id5).equalToWhenPresent(record::id5)
        set(id6).equalToWhenPresent(record::id6)
        set(id7).equalToWhenPresent(record::id7)
        set(emailaddress).equalToWhenPresent(record::emailaddress)
        set(from).equalToWhenPresent(record::from)
        set(active).equalToWhenPresent(record::active)
        set(active1).equalToWhenPresent(record::active1)
        set(active2).equalToWhenPresent(record::active2)
        set(dbClass).equalToWhenPresent(record::dbClass)
    }

fun AwfulTableMapper.updateByPrimaryKey(record: AwfulTableRecord) =
    update {
        set(firstFirstName).equalTo(record::firstFirstName)
        set(secondFirstName).equalTo(record::secondFirstName)
        set(thirdFirstName).equalTo(record::thirdFirstName)
        set(lastName).equalTo(record::lastName)
        set(eMail).equalTo(record::eMail)
        set(id1).equalTo(record::id1)
        set(id2).equalTo(record::id2)
        set(id5).equalTo(record::id5)
        set(id6).equalTo(record::id6)
        set(id7).equalTo(record::id7)
        set(emailaddress).equalTo(record::emailaddress)
        set(from).equalTo(record::from)
        set(active).equalTo(record::active)
        set(active1).equalTo(record::active1)
        set(active2).equalTo(record::active2)
        set(dbClass).equalTo(record::dbClass)
        where(customerId, isEqualTo(record::customerId))
    }

fun AwfulTableMapper.updateByPrimaryKeySelective(record: AwfulTableRecord) =
    update {
        set(firstFirstName).equalToWhenPresent(record::firstFirstName)
        set(secondFirstName).equalToWhenPresent(record::secondFirstName)
        set(thirdFirstName).equalToWhenPresent(record::thirdFirstName)
        set(lastName).equalToWhenPresent(record::lastName)
        set(eMail).equalToWhenPresent(record::eMail)
        set(id1).equalToWhenPresent(record::id1)
        set(id2).equalToWhenPresent(record::id2)
        set(id5).equalToWhenPresent(record::id5)
        set(id6).equalToWhenPresent(record::id6)
        set(id7).equalToWhenPresent(record::id7)
        set(emailaddress).equalToWhenPresent(record::emailaddress)
        set(from).equalToWhenPresent(record::from)
        set(active).equalToWhenPresent(record::active)
        set(active1).equalToWhenPresent(record::active1)
        set(active2).equalToWhenPresent(record::active2)
        set(dbClass).equalToWhenPresent(record::dbClass)
        where(customerId, isEqualTo(record::customerId))
    }
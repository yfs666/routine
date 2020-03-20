/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.108+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper

import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.RegexrenameDynamicSqlSupport.Regexrename
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.RegexrenameDynamicSqlSupport.Regexrename.address
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.RegexrenameDynamicSqlSupport.Regexrename.id
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.RegexrenameDynamicSqlSupport.Regexrename.name
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper.RegexrenameDynamicSqlSupport.Regexrename.zipCode
import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.model.RegexrenameRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun RegexrenameMapper.count(completer: CountCompleter) =
    countFrom(this::count, Regexrename, completer)

fun RegexrenameMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Regexrename, completer)

fun RegexrenameMapper.deleteByPrimaryKey(id_: Int) =
    delete {
        where(id, isEqualTo(id_))
    }

fun RegexrenameMapper.insert(record: RegexrenameRecord) =
    insert(this::insert, record, Regexrename) {
        map(id).toProperty("id")
        map(name).toProperty("name")
        map(address).toProperty("address")
        map(zipCode).toProperty("zipCode")
    }

fun RegexrenameMapper.insertSelective(record: RegexrenameRecord) =
    insert(this::insert, record, Regexrename) {
        map(id).toProperty("id")
        map(name).toPropertyWhenPresent("name", record::name)
        map(address).toPropertyWhenPresent("address", record::address)
        map(zipCode).toPropertyWhenPresent("zipCode", record::zipCode)
    }

private val columnList = listOf(id, name, address, zipCode)

fun RegexrenameMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Regexrename, completer)

fun RegexrenameMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Regexrename, completer)

fun RegexrenameMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Regexrename, completer)

fun RegexrenameMapper.selectByPrimaryKey(id_: Int) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun RegexrenameMapper.update(completer: UpdateCompleter) =
    update(this::update, Regexrename, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: RegexrenameRecord) =
    apply {
        set(id).equalTo(record::id)
        set(name).equalTo(record::name)
        set(address).equalTo(record::address)
        set(zipCode).equalTo(record::zipCode)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: RegexrenameRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(name).equalToWhenPresent(record::name)
        set(address).equalToWhenPresent(record::address)
        set(zipCode).equalToWhenPresent(record::zipCode)
    }

fun RegexrenameMapper.updateByPrimaryKey(record: RegexrenameRecord) =
    update {
        set(name).equalTo(record::name)
        set(address).equalTo(record::address)
        set(zipCode).equalTo(record::zipCode)
        where(id, isEqualTo(record::id))
    }

fun RegexrenameMapper.updateByPrimaryKeySelective(record: RegexrenameRecord) =
    update {
        set(name).equalToWhenPresent(record::name)
        set(address).equalToWhenPresent(record::address)
        set(zipCode).equalToWhenPresent(record::zipCode)
        where(id, isEqualTo(record::id))
    }
/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.1+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsblobsDynamicSqlSupport.Pkfieldsblobs
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsblobsDynamicSqlSupport.Pkfieldsblobs.blob1
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsblobsDynamicSqlSupport.Pkfieldsblobs.firstname
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsblobsDynamicSqlSupport.Pkfieldsblobs.id1
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsblobsDynamicSqlSupport.Pkfieldsblobs.id2
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkfieldsblobsDynamicSqlSupport.Pkfieldsblobs.lastname
import mbg.test.mb3.generated.dsql.kotlin.model.PkfieldsblobsRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun PkfieldsblobsMapper.count(completer: CountCompleter) =
    countFrom(this::count, Pkfieldsblobs, completer)

fun PkfieldsblobsMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Pkfieldsblobs, completer)

fun PkfieldsblobsMapper.deleteByPrimaryKey(id1_: Int, id2_: Int) =
    delete {
        where(id1, isEqualTo(id1_))
        and(id2, isEqualTo(id2_))
    }

fun PkfieldsblobsMapper.insert(record: PkfieldsblobsRecord) =
    insert(this::insert, record, Pkfieldsblobs) {
        map(id1).toProperty("id1")
        map(id2).toProperty("id2")
        map(firstname).toProperty("firstname")
        map(lastname).toProperty("lastname")
        map(blob1).toProperty("blob1")
    }

fun PkfieldsblobsMapper.insertMultiple(records: Collection<PkfieldsblobsRecord>) =
    insertMultiple(this::insertMultiple, records, Pkfieldsblobs) {
        map(id1).toProperty("id1")
        map(id2).toProperty("id2")
        map(firstname).toProperty("firstname")
        map(lastname).toProperty("lastname")
        map(blob1).toProperty("blob1")
    }

fun PkfieldsblobsMapper.insertMultiple(vararg records: PkfieldsblobsRecord) =
    insertMultiple(records.toList())

fun PkfieldsblobsMapper.insertSelective(record: PkfieldsblobsRecord) =
    insert(this::insert, record, Pkfieldsblobs) {
        map(id1).toPropertyWhenPresent("id1", record::id1)
        map(id2).toPropertyWhenPresent("id2", record::id2)
        map(firstname).toPropertyWhenPresent("firstname", record::firstname)
        map(lastname).toPropertyWhenPresent("lastname", record::lastname)
        map(blob1).toPropertyWhenPresent("blob1", record::blob1)
    }

private val columnList = listOf(id1, id2, firstname, lastname, blob1)

fun PkfieldsblobsMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Pkfieldsblobs, completer)

fun PkfieldsblobsMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Pkfieldsblobs, completer)

fun PkfieldsblobsMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Pkfieldsblobs, completer)

fun PkfieldsblobsMapper.selectByPrimaryKey(id1_: Int, id2_: Int) =
    selectOne {
        where(id1, isEqualTo(id1_))
        and(id2, isEqualTo(id2_))
    }

fun PkfieldsblobsMapper.update(completer: UpdateCompleter) =
    update(this::update, Pkfieldsblobs, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: PkfieldsblobsRecord) =
    apply {
        set(id1).equalTo(record::id1)
        set(id2).equalTo(record::id2)
        set(firstname).equalTo(record::firstname)
        set(lastname).equalTo(record::lastname)
        set(blob1).equalTo(record::blob1)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: PkfieldsblobsRecord) =
    apply {
        set(id1).equalToWhenPresent(record::id1)
        set(id2).equalToWhenPresent(record::id2)
        set(firstname).equalToWhenPresent(record::firstname)
        set(lastname).equalToWhenPresent(record::lastname)
        set(blob1).equalToWhenPresent(record::blob1)
    }

fun PkfieldsblobsMapper.updateByPrimaryKey(record: PkfieldsblobsRecord) =
    update {
        set(firstname).equalTo(record::firstname)
        set(lastname).equalTo(record::lastname)
        set(blob1).equalTo(record::blob1)
        where(id1, isEqualTo(record::id1))
        and(id2, isEqualTo(record::id2))
    }

fun PkfieldsblobsMapper.updateByPrimaryKeySelective(record: PkfieldsblobsRecord) =
    update {
        set(firstname).equalToWhenPresent(record::firstname)
        set(lastname).equalToWhenPresent(record::lastname)
        set(blob1).equalToWhenPresent(record::blob1)
        where(id1, isEqualTo(record::id1))
        and(id2, isEqualTo(record::id2))
    }
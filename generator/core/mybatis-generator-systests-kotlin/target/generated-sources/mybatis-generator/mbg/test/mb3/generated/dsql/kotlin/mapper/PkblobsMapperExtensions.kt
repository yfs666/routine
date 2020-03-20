/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.099+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import mbg.test.mb3.generated.dsql.kotlin.mapper.PkblobsDynamicSqlSupport.Pkblobs
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkblobsDynamicSqlSupport.Pkblobs.blob1
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkblobsDynamicSqlSupport.Pkblobs.blob2
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkblobsDynamicSqlSupport.Pkblobs.characterlob
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkblobsDynamicSqlSupport.Pkblobs.id
import mbg.test.mb3.generated.dsql.kotlin.model.PkblobsRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun PkblobsMapper.count(completer: CountCompleter) =
    countFrom(this::count, Pkblobs, completer)

fun PkblobsMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Pkblobs, completer)

fun PkblobsMapper.deleteByPrimaryKey(id_: Int) =
    delete {
        where(id, isEqualTo(id_))
    }

fun PkblobsMapper.insert(record: PkblobsRecord) =
    insert(this::insert, record, Pkblobs) {
        map(id).toProperty("id")
        map(blob1).toProperty("blob1")
        map(blob2).toProperty("blob2")
        map(characterlob).toProperty("characterlob")
    }

fun PkblobsMapper.insertMultiple(records: Collection<PkblobsRecord>) =
    insertMultiple(this::insertMultiple, records, Pkblobs) {
        map(id).toProperty("id")
        map(blob1).toProperty("blob1")
        map(blob2).toProperty("blob2")
        map(characterlob).toProperty("characterlob")
    }

fun PkblobsMapper.insertMultiple(vararg records: PkblobsRecord) =
    insertMultiple(records.toList())

fun PkblobsMapper.insertSelective(record: PkblobsRecord) =
    insert(this::insert, record, Pkblobs) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(blob1).toPropertyWhenPresent("blob1", record::blob1)
        map(blob2).toPropertyWhenPresent("blob2", record::blob2)
        map(characterlob).toPropertyWhenPresent("characterlob", record::characterlob)
    }

private val columnList = listOf(id, blob1, blob2, characterlob)

fun PkblobsMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Pkblobs, completer)

fun PkblobsMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Pkblobs, completer)

fun PkblobsMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Pkblobs, completer)

fun PkblobsMapper.selectByPrimaryKey(id_: Int) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun PkblobsMapper.update(completer: UpdateCompleter) =
    update(this::update, Pkblobs, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: PkblobsRecord) =
    apply {
        set(id).equalTo(record::id)
        set(blob1).equalTo(record::blob1)
        set(blob2).equalTo(record::blob2)
        set(characterlob).equalTo(record::characterlob)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: PkblobsRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(blob1).equalToWhenPresent(record::blob1)
        set(blob2).equalToWhenPresent(record::blob2)
        set(characterlob).equalToWhenPresent(record::characterlob)
    }

fun PkblobsMapper.updateByPrimaryKey(record: PkblobsRecord) =
    update {
        set(blob1).equalTo(record::blob1)
        set(blob2).equalTo(record::blob2)
        set(characterlob).equalTo(record::characterlob)
        where(id, isEqualTo(record::id))
    }

fun PkblobsMapper.updateByPrimaryKeySelective(record: PkblobsRecord) =
    update {
        set(blob1).equalToWhenPresent(record::blob1)
        set(blob2).equalToWhenPresent(record::blob2)
        set(characterlob).equalToWhenPresent(record::characterlob)
        where(id, isEqualTo(record::id))
    }
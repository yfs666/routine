/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.097+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import mbg.test.mb3.generated.dsql.kotlin.mapper.PkonlyDynamicSqlSupport.Pkonly
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkonlyDynamicSqlSupport.Pkonly.id
import mbg.test.mb3.generated.dsql.kotlin.mapper.PkonlyDynamicSqlSupport.Pkonly.seqNum
import mbg.test.mb3.generated.dsql.kotlin.model.PkonlyRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun PkonlyMapper.count(completer: CountCompleter) =
    countFrom(this::count, Pkonly, completer)

fun PkonlyMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Pkonly, completer)

fun PkonlyMapper.deleteByPrimaryKey(id_: Int, seqNum_: Int) =
    delete {
        where(id, isEqualTo(id_))
        and(seqNum, isEqualTo(seqNum_))
    }

fun PkonlyMapper.insert(record: PkonlyRecord) =
    insert(this::insert, record, Pkonly) {
        map(id).toProperty("id")
        map(seqNum).toProperty("seqNum")
    }

fun PkonlyMapper.insertMultiple(records: Collection<PkonlyRecord>) =
    insertMultiple(this::insertMultiple, records, Pkonly) {
        map(id).toProperty("id")
        map(seqNum).toProperty("seqNum")
    }

fun PkonlyMapper.insertMultiple(vararg records: PkonlyRecord) =
    insertMultiple(records.toList())

fun PkonlyMapper.insertSelective(record: PkonlyRecord) =
    insert(this::insert, record, Pkonly) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(seqNum).toPropertyWhenPresent("seqNum", record::seqNum)
    }

private val columnList = listOf(id, seqNum)

fun PkonlyMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Pkonly, completer)

fun PkonlyMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Pkonly, completer)

fun PkonlyMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Pkonly, completer)

fun PkonlyMapper.update(completer: UpdateCompleter) =
    update(this::update, Pkonly, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: PkonlyRecord) =
    apply {
        set(id).equalTo(record::id)
        set(seqNum).equalTo(record::seqNum)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: PkonlyRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(seqNum).equalToWhenPresent(record::seqNum)
    }
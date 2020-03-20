/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.105+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.readonly.mapper

import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields.birthDate
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields.datefield
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields.decimal100field
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields.decimal155field
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields.decimal30field
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields.decimal60field
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields.firstname
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields.id1
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields.id2
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields.lastname
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields.stringboolean
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields.timefield
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields.timestampfield
import mbg.test.mb3.generated.dsql.kotlin.readonly.mapper.PkfieldsDynamicSqlSupport.Pkfields.wierdField
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun PkfieldsMapper.count(completer: CountCompleter) =
    countFrom(this::count, Pkfields, completer)

private val columnList = listOf(id2, id1, firstname, lastname, datefield, timefield, timestampfield, decimal30field, decimal60field, decimal100field, decimal155field, wierdField, birthDate, stringboolean)

fun PkfieldsMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Pkfields, completer)

fun PkfieldsMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Pkfields, completer)

fun PkfieldsMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Pkfields, completer)

fun PkfieldsMapper.selectByPrimaryKey(id2_: Int, id1_: Int) =
    selectOne {
        where(id2, isEqualTo(id2_))
        and(id1, isEqualTo(id1_))
    }
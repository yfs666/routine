/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.097+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import mbg.test.mb3.common.StringBooleanTypeHandler
import mbg.test.mb3.generated.dsql.kotlin.model.PkfieldsRecord
import org.apache.ibatis.annotations.DeleteProvider
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.annotations.UpdateProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter

@Mapper
interface PkfieldsMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun count(selectStatement: SelectStatementProvider): Long

    @DeleteProvider(type=SqlProviderAdapter::class, method="delete")
    fun delete(deleteStatement: DeleteStatementProvider): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    fun insert(insertStatement: InsertStatementProvider<PkfieldsRecord>): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insertMultiple")
    fun insertMultiple(multipleInsertStatement: MultiRowInsertStatementProvider<PkfieldsRecord>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("PkfieldsRecordResult")
    fun selectOne(selectStatement: SelectStatementProvider): PkfieldsRecord?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="PkfieldsRecordResult", value = [
        Result(column="ID2", property="id2", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="ID1", property="id1", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="FIRSTNAME", property="firstname", jdbcType=JdbcType.VARCHAR),
        Result(column="LASTNAME", property="lastname", jdbcType=JdbcType.VARCHAR),
        Result(column="DATEFIELD", property="datefield", jdbcType=JdbcType.DATE),
        Result(column="TIMEFIELD", property="timefield", jdbcType=JdbcType.TIME),
        Result(column="TIMESTAMPFIELD", property="timestampfield", jdbcType=JdbcType.TIMESTAMP),
        Result(column="DECIMAL30FIELD", property="decimal30field", jdbcType=JdbcType.DECIMAL),
        Result(column="DECIMAL60FIELD", property="decimal60field", jdbcType=JdbcType.DECIMAL),
        Result(column="DECIMAL100FIELD", property="decimal100field", jdbcType=JdbcType.DECIMAL),
        Result(column="DECIMAL155FIELD", property="decimal155field", jdbcType=JdbcType.DECIMAL),
        Result(column="wierd\$Field", property="wierdField", jdbcType=JdbcType.INTEGER),
        Result(column="birth date", property="birthDate", jdbcType=JdbcType.DATE),
        Result(column="STRINGBOOLEAN", property="stringboolean", typeHandler=StringBooleanTypeHandler::class, jdbcType=JdbcType.CHAR)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<PkfieldsRecord>

    @UpdateProvider(type=SqlProviderAdapter::class, method="update")
    fun update(updateStatement: UpdateStatementProvider): Int
}
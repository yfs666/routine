/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.109+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper

import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.model.GeneratedalwaystestRecord
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
interface GeneratedalwaystestMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun count(selectStatement: SelectStatementProvider): Long

    @DeleteProvider(type=SqlProviderAdapter::class, method="delete")
    fun delete(deleteStatement: DeleteStatementProvider): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    fun insert(insertStatement: InsertStatementProvider<GeneratedalwaystestRecord>): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insertMultiple")
    fun insertMultiple(multipleInsertStatement: MultiRowInsertStatementProvider<GeneratedalwaystestRecord>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("GeneratedalwaystestRecordResult")
    fun selectOne(selectStatement: SelectStatementProvider): GeneratedalwaystestRecord?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="GeneratedalwaystestRecordResult", value = [
        Result(column="ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="ID_PLUS1", property="idPlus1", jdbcType=JdbcType.INTEGER),
        Result(column="ID_PLUS2", property="idPlus2", jdbcType=JdbcType.INTEGER),
        Result(column="BLOB1", property="blob1", jdbcType=JdbcType.VARBINARY)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<GeneratedalwaystestRecord>

    @UpdateProvider(type=SqlProviderAdapter::class, method="update")
    fun update(updateStatement: UpdateStatementProvider): Int
}
/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.1+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import mbg.test.mb3.generated.dsql.kotlin.model.PkfieldsblobsRecord
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
interface PkfieldsblobsMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun count(selectStatement: SelectStatementProvider): Long

    @DeleteProvider(type=SqlProviderAdapter::class, method="delete")
    fun delete(deleteStatement: DeleteStatementProvider): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    fun insert(insertStatement: InsertStatementProvider<PkfieldsblobsRecord>): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insertMultiple")
    fun insertMultiple(multipleInsertStatement: MultiRowInsertStatementProvider<PkfieldsblobsRecord>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("PkfieldsblobsRecordResult")
    fun selectOne(selectStatement: SelectStatementProvider): PkfieldsblobsRecord?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="PkfieldsblobsRecordResult", value = [
        Result(column="ID1", property="id1", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="ID2", property="id2", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="FIRSTNAME", property="firstname", jdbcType=JdbcType.VARCHAR),
        Result(column="LASTNAME", property="lastname", jdbcType=JdbcType.VARCHAR),
        Result(column="BLOB1", property="blob1", jdbcType=JdbcType.VARBINARY)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<PkfieldsblobsRecord>

    @UpdateProvider(type=SqlProviderAdapter::class, method="update")
    fun update(updateStatement: UpdateStatementProvider): Int
}
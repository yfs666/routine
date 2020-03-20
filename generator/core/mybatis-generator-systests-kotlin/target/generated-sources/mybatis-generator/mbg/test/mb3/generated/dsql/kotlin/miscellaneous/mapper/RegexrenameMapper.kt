/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.108+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.miscellaneous.mapper

import mbg.test.mb3.generated.dsql.kotlin.miscellaneous.model.RegexrenameRecord
import org.apache.ibatis.annotations.DeleteProvider
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectKey
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.annotations.UpdateProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter

@Mapper
interface RegexrenameMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun count(selectStatement: SelectStatementProvider): Long

    @DeleteProvider(type=SqlProviderAdapter::class, method="delete")
    fun delete(deleteStatement: DeleteStatementProvider): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    @SelectKey(statement=["call next value for TestSequence"], keyProperty="record.id", before=true, resultType=Int::class)
    fun insert(insertStatement: InsertStatementProvider<RegexrenameRecord>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("RegexrenameRecordResult")
    fun selectOne(selectStatement: SelectStatementProvider): RegexrenameRecord?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="RegexrenameRecordResult", value = [
        Result(column="CUST_ID", property="id", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="CUST_NAME", property="name", jdbcType=JdbcType.VARCHAR),
        Result(column="CUST_ADDRESS", property="address", jdbcType=JdbcType.VARCHAR),
        Result(column="ZIP_CODE", property="zipCode", jdbcType=JdbcType.CHAR)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<RegexrenameRecord>

    @UpdateProvider(type=SqlProviderAdapter::class, method="update")
    fun update(updateStatement: UpdateStatementProvider): Int
}
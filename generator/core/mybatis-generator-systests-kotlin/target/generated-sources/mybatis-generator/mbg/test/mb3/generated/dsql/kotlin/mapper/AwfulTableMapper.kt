/*
 * Auto-generated file. Created by MyBatis Generator
 * Generation date: 2020-03-11T11:07:31.101+08:00
 */
package mbg.test.mb3.generated.dsql.kotlin.mapper

import mbg.test.mb3.generated.dsql.kotlin.model.AwfulTableRecord
import org.apache.ibatis.annotations.DeleteProvider
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.InsertProvider
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Result
import org.apache.ibatis.annotations.ResultMap
import org.apache.ibatis.annotations.Results
import org.apache.ibatis.annotations.SelectProvider
import org.apache.ibatis.annotations.UpdateProvider
import org.apache.ibatis.type.JdbcType
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider
import org.mybatis.dynamic.sql.util.SqlProviderAdapter

@Mapper
interface AwfulTableMapper {
    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    fun count(selectStatement: SelectStatementProvider): Long

    @DeleteProvider(type=SqlProviderAdapter::class, method="delete")
    fun delete(deleteStatement: DeleteStatementProvider): Int

    @InsertProvider(type=SqlProviderAdapter::class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="record.customerId")
    fun insert(insertStatement: InsertStatementProvider<AwfulTableRecord>): Int

    @Insert(
        "\${insertStatement}"
    )
    @Options(useGeneratedKeys=true,keyProperty="records.customerId")
    fun insertMultiple(@Param("insertStatement") insertStatement: String, @Param("records") records: List<AwfulTableRecord>): Int

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @ResultMap("AwfulTableRecordResult")
    fun selectOne(selectStatement: SelectStatementProvider): AwfulTableRecord?

    @SelectProvider(type=SqlProviderAdapter::class, method="select")
    @Results(id="AwfulTableRecordResult", value = [
        Result(column="CuStOmEr iD", property="customerId", jdbcType=JdbcType.INTEGER, id=true),
        Result(column="first name", property="firstFirstName", jdbcType=JdbcType.VARCHAR),
        Result(column="FIRST_NAME", property="secondFirstName", jdbcType=JdbcType.VARCHAR),
        Result(column="FIRSTNAME", property="thirdFirstName", jdbcType=JdbcType.VARCHAR),
        Result(column="last name", property="lastName", jdbcType=JdbcType.VARCHAR),
        Result(column="E_MAIL", property="eMail", jdbcType=JdbcType.VARCHAR),
        Result(column="_id1", property="id1", jdbcType=JdbcType.INTEGER),
        Result(column="\$id2", property="id2", jdbcType=JdbcType.INTEGER),
        Result(column="id5_", property="id5", jdbcType=JdbcType.INTEGER),
        Result(column="id6\$", property="id6", jdbcType=JdbcType.INTEGER),
        Result(column="id7\$\$", property="id7", jdbcType=JdbcType.INTEGER),
        Result(column="EMAILADDRESS", property="emailaddress", jdbcType=JdbcType.VARCHAR),
        Result(column="from", property="from", jdbcType=JdbcType.VARCHAR),
        Result(column="ACTIVE", property="active", jdbcType=JdbcType.BIT),
        Result(column="ACTIVE1", property="active1", jdbcType=JdbcType.BOOLEAN),
        Result(column="ACTIVE2", property="active2", jdbcType=JdbcType.BIT),
        Result(column="CLASS", property="dbClass", jdbcType=JdbcType.VARCHAR)
    ])
    fun selectMany(selectStatement: SelectStatementProvider): List<AwfulTableRecord>

    @UpdateProvider(type=SqlProviderAdapter::class, method="update")
    fun update(updateStatement: UpdateStatementProvider): Int
}
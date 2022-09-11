package com.ucal.marketing.expo


import androidx.room.*

@Dao
interface VisitorDao {

    @Query("SELECT * FROM visitor_table")
    fun getAll(): List<Visitor>

    @Query("SELECT COUNT(id) FROM visitor_table")
    fun getRowCount(): Int

    @Query("SELECT * FROM visitor_table WHERE email LIKE :email LIMIT 1")
    suspend fun findByEmail(email: String): Visitor

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(visitor: Visitor)

    @Delete
    suspend fun delete(visitor: Visitor)

    @Query("DELETE FROM visitor_table")
    suspend fun deleteAll()

}
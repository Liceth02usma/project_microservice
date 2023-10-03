package com.mssecurity.mssecurity.Repositories;

import com.mssecurity.mssecurity.Models.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PermissionRepository extends MongoRepository<Permission,String> {
    @Query("{'url':?0,'method':?1}")
    Permission getPermission(String url, String method);

    @Query("{'_id':?0}")
    Permission getPermissionByID(String _id);
}



/// nota cambiar todas las urls otra vez, o mejor crear eso de nuevo.

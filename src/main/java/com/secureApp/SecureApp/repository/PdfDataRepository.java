package com.secureApp.SecureApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.secureApp.SecureApp.entity.PdfDtaEntity;

/**
 * @author Ankit Mohapatra
 * 
 */
@Repository
public interface PdfDataRepository extends JpaRepository<PdfDtaEntity,Long> {

}

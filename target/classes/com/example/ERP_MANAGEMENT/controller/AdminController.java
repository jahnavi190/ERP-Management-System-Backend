package com.example.ERP_MANAGEMENT.controller;

import com.example.ERP_MANAGEMENT.models.Admin;
import com.example.ERP_MANAGEMENT.service.AdminService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping({"/api/admin"})
public class AdminController {
   @Autowired
   private AdminService adminService;

   public AdminController() {
   }

   @PostMapping
   public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
      return ResponseEntity.ok(this.adminService.addAdmin(admin));
   }

   @PostMapping({"/login"})
   public ResponseEntity<String> loginAdmin(@RequestBody Admin loginDetails) {
      Admin admin = this.adminService.getAdminByUsername(loginDetails.getUsername());
      if (admin == null) {
         return ResponseEntity.status(404).body("No Admin Found");
      } else {
         return !admin.getPassword().equals(loginDetails.getPassword()) ? ResponseEntity.status(401).body("Password is incorrect") : ResponseEntity.ok("Success login");
      }
   }

   @GetMapping
   public ResponseEntity<List<Admin>> getAllAdmins() {
      return ResponseEntity.ok(this.adminService.getAllAdmins());
   }

   @GetMapping({"/{id}"})
   public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
      return (ResponseEntity)this.adminService.getAdminById(id).map(ResponseEntity::ok).orElse(ResponseEntity.status(404).body((Object)null));
   }

   @PutMapping({"/{id}"})
   public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
      try {
         return ResponseEntity.ok(this.adminService.updateAdmin(id, admin));
      } catch (RuntimeException var4) {
         return ResponseEntity.status(404).body((Object)null);
      }
   }

   @DeleteMapping({"/{id}"})
   public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
      this.adminService.deleteAdmin(id);
      return ResponseEntity.ok("Admin deleted successfully");
   }
}

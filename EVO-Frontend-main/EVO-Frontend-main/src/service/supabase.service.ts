    import { Injectable } from '@angular/core';
    import { createClient, SupabaseClient } from '@supabase/supabase-js';
    import { environment } from '../app/environment/env.test';

    @Injectable({
      providedIn: 'root'
    })
    export class SupabaseService {
      
      private supabase: SupabaseClient;

      constructor() {
        this.supabase = createClient(environment.supabaseURL, environment.supaBaseKey);
      }

      async uploadFile(bucketName: string, filePath: string, file: File) {
        const { data, error } = await this.supabase
          .storage
          .from(bucketName)
          .upload(filePath, file);

        if (error) {
          throw error;
        }

        return data;
      }

      getFileUrl(bucketName: string, filePath: string) {
        const { data } = this.supabase
          .storage
          .from(bucketName)
          .getPublicUrl(filePath);
        
        return data.publicUrl;
      }
    }
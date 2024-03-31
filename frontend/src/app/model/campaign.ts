 export class Campaign{
   
   constructor(
   public id:number,

   public creator_id:number,    
   public title:string,

    
   public  description:string,
    
   public goal_amount:bigint,

   public current_amount_funded:bigint,

   public start_date:Date,
     
   public end_date:Date,
     

   public status:string,


   public  category:string){}

}
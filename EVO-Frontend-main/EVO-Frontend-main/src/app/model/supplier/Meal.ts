import { MealType } from "../../../utils/MealType";

export class Meal {
  id?: number;
  name: string;
  description?: string;
  price: number;
  type: MealType;
  cateringId:number;

  constructor(data?: Partial<Meal>) {
    this.id = data?.id;
    this.name = data?.name || '';
    this.description = data?.description || '';
    this.price = data?.price || 0;
    this.type = data?.type || MealType.BREAKFAST;
    this.cateringId = data?.cateringId || 0;
  }

  getTotalPrice(quantity: number): number {
    return this.price * quantity;
  }
}
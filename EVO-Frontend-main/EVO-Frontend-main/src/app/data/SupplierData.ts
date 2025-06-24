import { SupplierType } from "../../utils/SupplierType";
import OLD_PhotographerExtraFeature from "../model/PhotographerExtraFeature";
import OLD_PhotographerPackage from "../model/PhotographerPackage";
import OLD_PhotographerReview from "../model/PhotographerReview";
import { New_Supplier } from "../model/supplier/NewSupplier";
import Supplier from "../model/supplier/Supplier";

export const suppliers: New_Supplier[] = [
  {
    id: 1,
    businessName: 'Kevin Lens Photography',
    businessContactNumber: '+94 712345678',
    businessEmail: 'kevin@lensphotography.com',
    description: 'Professional wedding and event photography services.',
    availability: true,
    category: SupplierType.PHOTOGRAPHY,
    imageUrl: 'https://images.pexels.com/photos/16120232/pexels-photo-16120232.jpeg?auto=compress&cs=tinysrgb&w=600'
  },
  {
    id: 2,
    businessName: 'Elegant Bites Catering',
    businessContactNumber: '+94 773456789',
    businessEmail: 'contact@elegantbites.lk',
    description: 'Exquisite catering for weddings, parties, and corporate events.',
    availability: true,
    category: SupplierType.BEAUTY_SALOON,
    imageUrl: 'https://images.pexels.com/photos/5088008/pexels-photo-5088008.jpeg?auto=compress&cs=tinysrgb&w=600'
  },
  {
    id: 3,
    businessName: 'Dreamscape Decorators',
    businessContactNumber: '+94 714567890',
    businessEmail: 'info@dreamscapedecor.lk',
    description: 'Creative decoration solutions for all your special occasions.',
    availability: false,
    category: SupplierType.VENUE,
    imageUrl: 'https://images.pexels.com/photos/1022186/pexels-photo-1022186.jpeg?auto=compress&cs=tinysrgb&w=600'
  }
];


export const allReviews: OLD_PhotographerReview[][] = [
  [
    {
      reviewId: 1,
      rating: 'five',
      date: '2024-03-10',
      comment: "Kevin's wedding photos were beyond beautiful. He captured every moment perfectly!"
    },
    {
      reviewId: 2,
      rating: 'four',
      date: '2024-04-15',
      comment: "The catering was delicious and delivered on time. Would definitely book Elegant Bites again."
    },
    {
      reviewId: 3,
      rating: 'five',
      date: '2024-05-05',
      comment: "Dreamscape transformed our event space into something magical. Highly recommended!"
    }
  ],
  [
    {
      reviewId: 4,
      rating: 'five',
      date: '2024-06-12',
      comment: "Excellent portrait session. Very patient and professional."
    },
    {
      reviewId: 5,
      rating: 'four',
      date: '2024-06-15',
      comment: "Great customer service and amazing shots!"
    },
    {
      reviewId: 6,
      rating: 'five',
      date: '2024-06-18',
      comment: "Would hire them again without hesitation!"
    }
  ],
  [
    {
      reviewId: 7,
      rating: 'five',
      date: '2024-07-01',
      comment: "Outstanding video quality and professional editing!"
    },
    {
      reviewId: 8,
      rating: 'four',
      date: '2024-07-04',
      comment: "Good coordination and quality output."
    },
    {
      reviewId: 9,
      rating: 'five',
      date: '2024-07-06',
      comment: "Highly recommend for any corporate event!"
    }
  ]
];

 export const packages:OLD_PhotographerPackage[][] = [[
    {
      id: 1,
      name: "Wedding Package",
      price: 2500,
      description: "Full day wedding coverage",
      items: [
        "4 hours of coverage",
        "2 photographers",
        "100 edited photos",
        "1 photo album"
      ]
    },
    {
      id: 2,
      name: "Portrait Session",
      price: 5000,
      description: "1-hour portrait session",
      items: [
        "10 hour of coverage",
        "5 photographer",
        "200 edited photos",
        "2 photo album"
      ]
    },
    {
      id: 3,
      name: "Corporate Event",
      price: 3000,
      description: "Professional coverage for business events",
      items: [
        "6 hours of coverage",
        "8 photographers",
        "300 edited photos",
        "1 photo album"
      ]
    }
  ],
  [
    {
      id: 1,
      name: "Wedding Package",
      price: 2500,
      description: "Full day wedding coverage",
      items: [
        "4 hours of coverage",
        "2 photographers",
        "100 edited photos",
        "1 photo album"
      ]
    },
    {
      id: 2,
      name: "Portrait Session",
      price: 5000,
      description: "1-hour portrait session",
      items: [
        "10 hour of coverage",
        "5 photographer",
        "200 edited photos",
        "2 photo album"
      ]
    },
    {
      id: 3,
      name: "Corporate Event",
      price: 3000,
      description: "Professional coverage for business events",
      items: [
        "6 hours of coverage",
        "8 photographers",
        "300 edited photos",
        "1 photo album"
      ]
    }
  ],
  [
    {
      id: 1,
      name: "Wedding Package",
      price: 2500,
      description: "Full day wedding coverage",
      items: [
        "4 hours of coverage",
        "2 photographers",
        "100 edited photos",
        "1 photo album"
      ]
    },
    {
      id: 2,
      name: "Portrait Session",
      price: 5000,
      description: "1-hour portrait session",
      items: [
        "10 hour of coverage",
        "5 photographer",
        "200 edited photos",
        "2 photo album"
      ]
    },
    {
      id: 3,
      name: "Corporate Event",
      price: 3000,
      description: "Professional coverage for business events",
      items: [
        "6 hours of coverage",
        "8 photographers",
        "300 edited photos",
        "1 photo album"
      ]
    }
  ]
];


export const reviews: OLD_PhotographerReview[][] = [
  [
    {
      reviewId: 1,
      rating: 'five',
      date: '2024-03-10',
      comment: "Kevin's wedding photos were beyond beautiful. He captured every moment perfectly!"
    },
    {
      reviewId: 2,
      rating: 'four',
      date: '2024-04-15',
      comment: "The catering was delicious and delivered on time. Would definitely book Elegant Bites again."
    },
    {
      reviewId: 3,
      rating: 'five',
      date: '2024-05-05',
      comment: "Dreamscape transformed our event space into something magical. Highly recommended!"
    }
  ],
  [
    {
      reviewId: 1,
      rating: 'five',
      date: '2024-03-10',
      comment: "Kevin's wedding photos were beyond beautiful. He captured every moment perfectly!"
    },
    {
      reviewId: 2,
      rating: 'four',
      date: '2024-04-15',
      comment: "The catering was delicious and delivered on time. Would definitely book Elegant Bites again."
    },
    {
      reviewId: 3,
      rating: 'five',
      date: '2024-05-05',
      comment: "Dreamscape transformed our event space into something magical. Highly recommended!"
    }
  ],
  [
    {
      reviewId: 1,
      rating: 'five',
      date: '2024-03-10',
      comment: "Kevin's wedding photos were beyond beautiful. He captured every moment perfectly!"
    },
    {
      reviewId: 2,
      rating: 'four',
      date: '2024-04-15',
      comment: "The catering was delicious and delivered on time. Would definitely book Elegant Bites again."
    },
    {
      reviewId: 3,
      rating: 'five',
      date: '2024-05-05',
      comment: "Dreamscape transformed our event space into something magical. Highly recommended!"
    }
  ]
];

const features: OLD_PhotographerExtraFeature[][]  = [
  [ 
    { "id": 1, "feature": "Standard Prints", "price": 100 },
    { "id": 2, "feature": "Basic Album", "price": 120 },
    { "id": 3, "feature": "Photo Editing", "price": 80 },
    { "id": 4, "feature": "USB Delivery", "price": 50 },
    { "id": 5, "feature": "Soft Copy", "price": 60 }
  ],
  [ 
    { "id": 1, "feature": "Standard Prints", "price": 100 },
    { "id": 2, "feature": "Basic Album", "price": 120 },
    { "id": 3, "feature": "Photo Editing", "price": 80 },
    { "id": 4, "feature": "USB Delivery", "price": 50 },
    { "id": 5, "feature": "Soft Copy", "price": 60 }
  ],
  [ 
    { "id": 1, "feature": "Standard Prints", "price": 100 },
    { "id": 2, "feature": "Basic Album", "price": 120 },
    { "id": 3, "feature": "Photo Editing", "price": 80 },
    { "id": 4, "feature": "USB Delivery", "price": 50 },
    { "id": 5, "feature": "Soft Copy", "price": 60 }
  ],
  [ 
    { "id": 1, "feature": "Standard Prints", "price": 100 },
    { "id": 2, "feature": "Basic Album", "price": 120 },
    { "id": 3, "feature": "Photo Editing", "price": 80 },
    { "id": 4, "feature": "USB Delivery", "price": 50 },
    { "id": 5, "feature": "Soft Copy", "price": 60 }
  ]
];


export function getSupplierById(id: number): New_Supplier | undefined {
  // call api 

  //if not working
  return suppliers.find((supplier) => supplier.id === id);
}

export function getSupplierReviewById(id: number): OLD_PhotographerReview[]  {
  // call api 

  //if not working  
  return allReviews[id];
}

export function getSupplierPackageById(id: number): OLD_PhotographerPackage[]  {
  // call api 

  //if not working  
  return packages[id];
}

export function getaSupplierFeture(id: number): OLD_PhotographerExtraFeature[] {
  // call api 

  //if not working  
  return features[id];
}
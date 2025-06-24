export class Pool {
  id: number | null;
  size: string;
  depth: number;

  constructor(id: number | null = null, size: string = '', depth: number = 0) {
    this.id = id;
    this.size = size;
    this.depth = depth;
  }
}

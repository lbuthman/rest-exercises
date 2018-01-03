export class Visitor {

  public id: number;
  public name: string;
  public greeted: boolean;

  constructor(name: string, greeted: boolean, id?: number) {
    this.name = name;
    this.greeted = greeted;
    this.id = id;
  }
}

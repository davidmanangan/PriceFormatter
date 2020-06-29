export class PriceFormat {
    constructor(
        public rawPrice:string,
        public format:string,
        public dpl:string,
        public fpl:string,
        public scale:string,
        public bf?:string,
        public dp?:string,
        public fp?:string
    ){}
}

import Home from "../components/ingredient/home.js";
import About from "../components/ingredient/about";
import Contact from "../components/ingredient/contact.js";
import Lich from "../components/ingredient/lich.js";
import DatVe from "../components/ingredient/Datve/datve.js";
import Login from "../components/ingredient/login";
import Port from "../components/ingredient/port";
import HistoryTicket from "../components/ingredient/lichsudatve";
import Forsefl from "../components/ingredient/profile";
import Register from "../components/ingredient/dangky";
import ThanhToanThanhCong from "../components/ingredient/thanhtoanthanhcong.js";
import UserDetailsPage from "../components/ingredient/profile.js";
import LichSuDatVe from "../components/ingredient/lichsudatve";
import ForgotPassword from "../components/ingredient/quenmk.js";
import CheckTicket from "../components/ingredient/checkve.js";
import Dopayment from "../components/ingredient/dopayment.js";
import DoiMk from "../components/ingredient/doimk.js";

const publicRoutes = [
    {path: '/', component: Home},
    {path: '/about', component: About},
    {path: '/contact', component: Contact},
    {path: '/lich', component: Lich},
    {path: '/dat-ve', component: DatVe},
    {path: '/login', component: Login},
    {path: '/ben-tau', component: Port},
    {path: '/lich-su', component: HistoryTicket},
    {path: '/thong-tin', component: Forsefl},
    {path: '/dang-ky', component: Register},
    {path: '/profile', component: UserDetailsPage},
    {path: '/lich-su-dat-ve', component: LichSuDatVe},
    {path: '/quen-mat-khau', component: ForgotPassword},
    {path: '/dat-ve/thanh-toan-thanh-cong', component: ThanhToanThanhCong},
     {path: '/kiem-tra-ve', component: CheckTicket},
          {path: '/dopayment', component: Dopayment},
                    {path: '/doi-mat-khau', component: DoiMk}



    
];

export {publicRoutes};

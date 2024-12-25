// src/components/Navbar.js
import React, {useState} from 'react';
import {useAuth} from "../../../AuthContext";
import './style.css';
import LanguageSwitcher from "../LanguageSwitcher";
import {useTranslation} from "react-i18next";

const Navbar = () => {
    const {t} = useTranslation();
    const {isLoggedIn, logout} = useAuth();
    const username = localStorage.getItem('us');
    const [isOpen, setIsOpen] = useState(false);
    const toggleMenu = () => {
        setIsOpen(!isOpen);
    };

    return (
        <nav className="bg-white p-4 navbar-responsive  relative z-50">
            <div className=" mx-auto flex items-center w-full">
              
                    <div className="text-black text-lg font-bold flex w-8/12 lg:w-3/12 lg:justify-start">
                        <div className='mt-2'>
                            <a href='/' alt='home'>
                            <img className="h-10 w-15" alt='' src="/img/logo_footer.png"/>
                            </a>
                        </div>
                        <div className="flex justify-center items-center">
                          <a href='/' alt='home'>
                            <p className='ml-1 lg:text-2xl lg:mx-4 text-base text-center'>Saigon Waterbus</p>
                               </a>
                        </div>
                    </div>
             
                <div 
                    className={`lg:flex lg:flex-row lg:w-6/12 lg:justify-center lg:items-center ${isOpen ? 'flex bg-slate-300' : 'hidden'} flex-col lg:static absolute top-16 md:content-center left-0 lg:top-0 w-full  border-t lg:border-none`}>
 <div className="flex flex-col items-start py-2 w-full lg:hidden">
    {isLoggedIn ? (
        <div className="w-full">
            <div className="flex items-center flex-wrap gap-2 justify-center"> 
                <img src="/img/icon/account.png" alt="User" className="rounded-full mb-2" />
                <a href='/profile' alt='thong tin ca nhan'>
                    <h3 className="text-black font-semibold whitespace-nowrap overflow-hidden text-ellipsis max-w-full">
                        <span className='text-sky-700'>{username}</span>
                    </h3>
                </a>
            </div>
            <div className="flex w-full mt-2 justify-center relative space-x-6">
                <a className="flex items-center bg-blue-500 text-white rounded text-xs font-bold p-2 hover:bg-blue-600 focus:outline-none" href='/profile'>
                    <div className='mr-2'>
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor" className="w-4 h-4">
                            <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6ZM12.735 14c.618 0 1.093-.561.872-1.139a6.002 6.002 0 0 0-11.215 0c-.22.578.254 1.139.872 1.139h9.47Z" />
                        </svg>
                    </div>
                    {t("menu.information")}
                </a>
                <button onClick={logout} className="flex items-center bg-blue-500 text-white rounded text-xs font-bold p-2 hover:bg-blue-600 focus:outline-none ">
                    <div className='mr-2'>
                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor" className="w-4 h-4">
                            <path fillRule="evenodd" d="M14 4.75A2.75 2.75 0 0 0 11.25 2h-3A2.75 2.75 0 0 0 5.5 4.75v.5a.75.75 0 0 0 1.5 0v-.5c0-.69.56-1.25 1.25-1.25h3c.69 0 1.25.56 1.25 1.25v6.5c0 .69-.56 1.25-1.25 1.25h-3c-.69 0-1.25-.56-1.25-1.25v-.5a.75.75 0 0 0-1.5 0v.5A2.75 2.75 0 0 0 8.25 14h3A2.75 2.75 0 0 0 14 11.25v-6.5Zm-9.47.47a.75.75 0 0 0-1.06 0L1.22 7.47a.75.75 0 0 0 0 1.06l2.25 2.25a.75.75 0 1 0 1.06-1.06l-.97-.97h7.19a.75.75 0 0 0 0-1.5H3.56l.97-.97a.75.75 0 0 0 0-1.06Z" clipRule="evenodd" />
                        </svg>
                    </div>
                    {t("menu.logOut")}
                </button>
                        <button className="flex items-center bg-blue-500 text-white rounded text-xs font-bold p-2 hover:bg-blue-600 focus:outline-none">
                        <img src='/img/icon/ticket.png' className='inline-block w-5 h-4 mr-2' alt='logout'></img>
                        <a className="font-semibold " href="/lich-su-dat-ve">{t("menu.bookingHistory")}</a>
                        </button>
            </div>
        </div>
    ) : (
        <a className="flex w-full font-bold md:text-base justify-center hover:text-gray-600 gap-2" href="/login">
            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5.121 17.804A13.937 13.937 0 0112 16c2.5 0 4.847.655 6.879 1.804M15 10a3 3 0 11-6 0 3 3 0 016 0zm6 2a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
            {t("menu.logIn")}
        </a>
    )}
</div>
        <div className='text-sm block lg:flex lg:content-center  lg:text-sm xl:text-sm 2xl:text-base ml-2 md:ml-0'>
            <ul className="block lg:flex w-full lg:flex-row lg:items-center lg:space-x-20 lg:py-0 py-2 font-semibold">
                <li className="">
                    <a href="/" className="block py-2 text-black hover:text-red">{t("menu.home")}</a>
                </li>
                <li className="">
                    <a href="/ben-tau" className="block py-2 text-black hover:text-red">{t("menu.wharf")}</a>
                </li>
                <li className="">
                    <a href="/lich" className="block py-2 text-black hover:text-red">{t("menu.departureSchedule")}</a>
                </li>
                <li className="">
                    <a href="/dat-ve" className="block py-2 text-black hover:text-red">{t("menu.bookTickets")}</a>
                </li>
                   <li className="">
                    <a href="/kiem-tra-ve" className="block py-2 text-black hover:text-red">{t("checkTicket.checkTicket")}</a>
                </li>
                <li className="">
                    <a href="/contact" className="block py-2 text-black hover:text-red">{t("menu.contact")}</a>
                </li>

            </ul>
        </div>
       </div>
                <div className='flex w-4/12 lg:w-3/12 lg:justify-end justify-end text-sm'>
                {isLoggedIn ? (
                            <div className="hidden lg:flex items-center relative mx-2">
                                    <div className="text-black flex items-center relative group">
                                <a href='/profile' alt='thong tin ca nhan' className="flex items-center">
                                    <img src='/img/icon/userheader.png' className='inline-block' alt='user'></img>
                                    <span className='ml-2 text-sm md:text-xs lg:text-xs xl:text-xs 2xl:text-base font-semibold truncate max-w-[100px] lg:max-w-[150px] xl:max-w-[200px]'>
                                        {username}
                                    </span>
                                </a>

                                        <div className='p-6 w-64 absolute top-full left-0  opacity-0 invisible group-hover:opacity-100 group-hover:visible  transition-opacity duration-300  bg-slate-100 shadow-lg rounded  z-10'>
                                            <a href='/profile' 
                                                className="text-black hover:text-green focus:outline-none flex items-center mb-2">
                                                <img src='/img/icon/account.png' className='inline-block w-5 h-4 mr-2' alt='account'></img>
                                                <span className='font-semibold '>{t("menu.information")}</span>
                                            </a>
                                            <a href='/doi-mat-khau' 
                                                className="text-black hover:text-green focus:outline-none flex items-center mb-2">
                                                <img src='/img/icon/account.png' className='inline-block w-5 h-4 mr-2' alt='account'></img>
                                                <span className='font-semibold '>{t("menu.changepass")}</span>
                                                
                                            </a>               
                                        <button className="text-black hover:text-red focus:outline-none flex items-center mb-2">
                                        <img src='/img/icon/ticket.png' className='inline-block w-5 h-4 mr-2' alt='logout'></img>
                                            <a className="font-semibold " href="/lich-su-dat-ve">{t("menu.bookingHistory")}</a>
                                        </button>
                                    
                                            <button onClick={logout}
                                                className="text-black hover:text-red focus:outline-none flex items-center">
                                                <img src='/img/icon/log-out.png' className='inline-block w-5 h-4 mr-2' alt='logout'></img>
                                                <span className='font-semibold '>{t("menu.logOut")}</span>
                                            </button>

                                        </div>

                                    </div>
                            </div>

                ) : (
                    <a className="hidden font-semibold lg:flex items-center hover:text-gray-200" href="/login">
                        <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 hover:text-gray-200 mr-2" fill="none"
                             viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2}
                                  d="M5.121 17.804A13.937 13.937 0 0112 16c2.5 0 4.847.655 6.879 1.804M15 10a3 3 0 11-6 0 3 3 0 016 0zm6 2a9 9 0 11-18 0 9 9 0 0118 0z"/>
                        </svg>
                        {t("menu.logIn")}
                    </a>
                )}
                    <div className="block lg:hidden content-center">
                        <button onClick={toggleMenu} className="hover:text-white focus:outline-none flex justify-center items-center">
                            {isOpen ?
                                <img width="24" height="24" src="https://img.icons8.com/ios-filled/50/delete-sign--v1.png" alt="delete-sign--v1" /> :
                                <img width="24" height="24" src="https://img.icons8.com/ios-filled/36/menu--v1.png" alt="menu--v1" />}
                        </button>
                </div>
                <LanguageSwitcher className="justify-end "/></div>
            </div>
                                

        </nav>
    );
};

export default Navbar;

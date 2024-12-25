// src/Lich.js
import React from 'react';
import { useTranslation } from 'react-i18next';

function Lich() {
    const { t } = useTranslation();

    return (
        <div className='text-sm 2xl:text-base'>
                    <section className="p-4 ">
                        <div className="daily space-y-2 sm:space-y-4">
                            <h2 className="text-sm sm:text-base lg:text-lg font-bold">{t('dailyTrips')}</h2>
                            <span className="text-sm sm:text-sm lg:text-base">{t('tripsDescription')}</span>
                        </div>
                    </section>

            <section className="px-4">
                <div className="container mx-auto">
                    <div className="flex flex-wrap">
                        <div className="w-full sm:w-1/2 mt-2">
                            <div className="nenxanh bg-green-100 p-2 rounded-md sm:mr-2">
                                <div className="ld mb-4">
                                    <h4 className="text-lg font-semibold flex items-center">
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                             stroke-width="1.5" stroke="currentColor" className="w-6 h-6">
                                            <path stroke-linecap="round" stroke-linejoin="round"
                                                  d="M6.75 3v2.25M17.25 3v2.25M3 18.75V7.5a2.25 2.25 0 0 1 2.25-2.25h13.5A2.25 2.25 0 0 1 21 7.5v11.25m-18 0A2.25 2.25 0 0 0 5.25 21h13.5A2.25 2.25 0 0 0 21 18.75m-18 0v-7.5A2.25 2.25 0 0 1 5.25 9h13.5A2.25 2.25 0 0 1 21 11.25v7.5m-9-6h.008v.008H12v-.008ZM12 15h.008v.008H12V15Zm0 2.25h.008v.008H12v-.008ZM9.75 15h.008v.008H9.75V15Zm0 2.25h.008v.008H9.75v-.008ZM7.5 15h.008v.008H7.5V15Zm0 2.25h.008v.008H7.5v-.008Zm6.75-4.5h.008v.008h-.008v-.008Zm0 2.25h.008v.008h-.008v-.008Zm0 2.25h.008v.008h-.008V15Zm0 2.25h.008v.008h-.008v-.008Zm2.25-4.5h.008v.008H16.5v-.008Zm0 2.25h.008v.008H16.5V15Z"/>
                                        </svg>
                                        <span className="text text-sm sm:text-sm lg:text-base">{t('outboundTrip')}</span>
                                    </h4>
                                </div>
                                <table className="min-w-full bg-white border border-gray-200 text-sm sm:text-sm lg:text-base">
                                    <thead>
                                    <tr className="bg-gray-200">
                                        <th className="px-2 md:px-4 py-2">Bạch Đằng</th>
                                        <th className="px-2 md:px-4 py-2">Bình An</th>
                                        <th className="px-2 md:px-4 py-2">Thanh Đa</th>
                                        <th className="px-2 md:px-4 py-2">H.B. Chánh</th>
                                        <th className="px-2 md:px-4 py-2">Linh Đông</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr className="bg-white">
                                        <td className="px-2 md:px-4 py-2">8:30</td>
                                        <td className="px-2 md:px-4 py-2">8:45</td>
                                        <td className="px-2 md:px-4 py-2">9:02</td>
                                        <td className="px-2 md:px-4 py-2">9:12</td>
                                        <td className="px-2 md:px-4 py-2">9:22</td>
                                    </tr>
                                    <tr className="bg-gray-100">
                                        <td className="px-2 md:px-4 py-2">10:40</td>
                                        <td className="px-2 md:px-4 py-2">10:55</td>
                                        <td className="px-2 md:px-4 py-2">11:12</td>
                                        <td className="px-2 md:px-4 py-2">11:22</td>
                                        <td className="px-2 md:px-4 py-2">11:32</td>
                                    </tr>
                                    <tr className="bg-white">
                                        <td className="px-2 md:px-4 py-2">14:30</td>
                                        <td className="px-2 md:px-4 py-2">14:45</td>
                                        <td className="px-2 md:px-4 py-2">15:02</td>
                                        <td className="px-2 md:px-4 py-2">15:12</td>
                                        <td className="px-2 md:px-4 py-2">15:22</td>
                                    </tr>
                                    <tr className="bg-gray-100">
                                        <td className="px-2 md:px-4 py-2">17:10</td>
                                        <td className="px-2 md:px-4 py-2">17:25</td>
                                        <td className="px-2 md:px-4 py-2">17:42</td>
                                        <td className="px-2 md:px-4 py-2">17:52</td>
                                        <td className="px-2 md:px-4 py-2">18:02</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div className="w-full sm:w-1/2 mt-2 ">
                            <div className="nenhong bg-pink-100 p-2 rounded-md">
                                <div className="ld mb-4">
                                    <h4 className="text-lg font-semibold flex items-center">
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                             stroke-width="1.5" stroke="currentColor" className="w-6 h-6">
                                            <path stroke-linecap="round" stroke-linejoin="round" d="M6.75 3v2.25M17.25 3v2.25M3 18.75V7.5a2.25 2.25 0 0 1 2.25-2.25h13.5A2.25 2.25 0 0 1 21 7.5v11.25m-18 0A2.25 2.25 0 0 0 5.25 21h13.5A2.25 2.25 0 0 0 21 18.75m-18 0v-7.5A2.25 2.25 0 0 1 5.25 9h13.5A2.
                      25 2.25 0 0 1 21 11.25v7.5m-9-6h.008v.008H12v-.008ZM12 15h.008v.008H12V15Zm0 2.25h.008v.008H12v-.008ZM9.75 15h.008v.008H9.75V15Zm0 2.25h.008v.008H9.75v-.008ZM7.5 15h.008v.008H7.5V15Zm0 2.25h.008v.008H7.5v-.008Zm6.75-4.5h.008v.008h-.008v-.008Zm0 2.25h.008v.008h-.008v-.008Zm0 2.25h.008v.008h-.008V15Zm0 2.25h.008v.008h-.008v-.008Zm2.25-4.5h.008v.008H16.5v-.008Zm0 2.25h.008v.008H16.5V15Z"/>
                                        </svg>

                                        <span className="text text-sm sm:text-sm lg:text-base">{t('returnTrip')}</span>
                                    </h4>
                                </div>
                                <table className="min-w-full bg-white border border-gray-200 text-sm sm:text-sm lg:text-base">
                                    <thead>
                                    <tr className="bg-gray-200">
                                        <th className="px-2 md:px-4 py-2">Linh Đông</th>
                                        <th className="px-2 md:px-4 py-2">H.B. Chánh</th>
                                        <th className="px-2 md:px-4 py-2">Thanh Đa</th>
                                        <th className="px-2 md:px-4 py-2">Bình An</th>
                                        <th className="px-2 md:px-4 py-2">Bạch Đằng</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr className="bg-white">
                                        <td className="px-2 md:px-4 py-2">9:40</td>
                                        <td className="px-2 md:px-4 py-2">9:50</td>
                                        <td className="px-2 md:px-4 py-2">10:00</td>
                                        <td className="px-2 md:px-4 py-2">10:17</td>
                                        <td className="px-2 md:px-4 py-2">10:32</td>
                                    </tr>
                                    <tr className="bg-gray-100">
                                        <td className="px-2 md:px-4 py-2">12:00</td>
                                        <td className="px-2 md:px-4 py-2">12:10</td>
                                        <td className="px-2 md:px-4 py-2">12:20</td>
                                        <td className="px-2 md:px-4 py-2">12:37</td>
                                        <td className="px-2 md:px-4 py-2">12:52</td>
                                    </tr>
                                    <tr className="bg-white">
                                        <td className="px-2 md:px-4 py-2">15:40</td>
                                        <td className="px-2 md:px-4 py-2">15:50</td>
                                        <td className="px-2 md:px-4 py-2">16:00</td>
                                        <td className="px-2 md:px-4 py-2">16:17</td>
                                        <td className="px-2 md:px-4 py-2">16:32</td>
                                    </tr>
                                    <tr className="bg-gray-100">
                                        <td className="px-2 md:px-4 py-2">18:15</td>
                                        <td className="px-2 md:px-4 py-2">18:25</td>
                                        <td className="px-2 md:px-4 py-2">18:35</td>
                                        <td className="px-2 md:px-4 py-2">18:52</td>
                                        <td className="px-2 md:px-4 py-2">19:07</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section className="p-4">
                <div className="">
                    <h2 className="text-sm sm:text-sm  lg:text-base font-bold">{t('binhAnTrip')}</h2>
                    <p>{t('binhAnTripDescription')}</p>
                </div>
                <div className="ly">
                    <div className="luuy">
                        <p className="text-red-600 font-semibold text-sm sm:text-sm lg:text-base">
                            <strong>{t('noteLich')}</strong>
                        </p>
                    </div>
                </div>
            </section>
            <section className="px-4">
                <div className="container mx-auto">
                    <div className="flex flex-wrap">
                        <div className="w-full sm:w-1/2">
                            <div className="bg-blue-100 p-2 rounded-md sm:mr-2">
                                <div className="flex items-center mb-4">
                                    <h4 className="text-lg font-semibold flex items-center">
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                             stroke-width="1.5" stroke="currentColor" className="w-6 h-6">
                                            <path stroke-linecap="round" stroke-linejoin="round"
                                                  d="M6.75 3v2.25M17.25 3v2.25M3 18.75V7.5a2.25 2.25 0 0 1 2.25-2.25h13.5A2.25 2.25 0 0 1 21 7.5v11.25m-18 0A2.25 2.25 0 0 0 5.25 21h13.5A2.25 2.25 0 0 0 21 18.75m-18 0v-7.5A2.25 2.25 0 0 1 5.25 9h13.5A2.25 2.25 0 0 1 21 11.25v7.5m-9-6h.008v.008H12v-.008ZM12 15h.008v.008H12V15Zm0 2.25h.008v.008H12v-.008ZM9.75 15h.008v.008H9.75V15Zm0 2.25h.008v.008H9.75v-.008ZM7.5 15h.008v.008H7.5V15Zm0 2.25h.008v.008H7.5v-.008Zm6.75-4.5h.008v.008h-.008v-.008Zm0 2.25h.008v.008h-.008V15Zm0 2.25h.008v.008h-.008v-.008Zm2.25-4.5h.008v.008H16.5v-.008Zm0 2.25h.008v.008H16.5V15Z"/>
                                        </svg>
                                        <span className="text text-sm sm:text-sm lg:text-base">{t('outboundTrip')}</span>
                                    </h4>
                                </div>
                                <table className="min-w-full bg-white border border-gray-200 text-sm sm:text-sm lg:text-base">
                                    <thead>
                                    <tr className="bg-gray-200">
                                        <th className="px-2 md:px-4 py-2" style={{width: '192.292px'}}>Mã chuyến</th>
                                        <th className="px-2 md:px-4 py-2" style={{width: '141.656px'}}>Bạch Đằng</th>
                                        <th className="px-2 md:px-4 py-2" style={{width: '106.552px'}}>Bình An</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 1 (*)</td>
                                        <td className="px-2 md:px-4 py-2">16:00</td>
                                        <td className="px-2 md:px-4 py-2">16:15</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 3 (*)</td>
                                        <td className="px-2 md:px-4 py-2">16:30</td>
                                        <td className="px-2 md:px-4 py-2">16:45</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 5</td>
                                        <td className="px-2 md:px-4 py-2">17:00</td>
                                        <td className="px-2 md:px-4 py-2">17:15</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 7</td>
                                        <td className="px-2 md:px-4 py-2">17:30</td>
                                        <td className="px-2 md:px-4 py-2">17:45</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 9</td>
                                        <td className="px-2 md:px-4 py-2">18:00</td>
                                        <td className="px-2 md:px-4 py-2">18:15</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 11</td>
                                        <td className="px-2 md:px-4 py-2">18:30</td>
                                        <td className="px-2 md:px-4 py-2">18:45</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 13</td>
                                        <td className="px-2 md:px-4 py-2">19:00</td>
                                        <td className="px-2 md:px-4 py-2">19:15</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 15</td>
                                        <td className="px-2 md:px-4 py-2">19:30</td>
                                        <td className="px-2 md:px-4 py-2">19:45</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 13 (*)</td>
                                        <td className="px-2 md:px-4 py-2">19:45</td>
                                        <td className="px-2 md:px-4 py-2">20:00</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 17</td>
                                        <td className="px-2 md:px-4 py-2">20:00</td>
                                        <td className="px-2 md:px-4 py-2">20:15</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 19</td>
                                        <td className="px-2 md:px-4 py-2">20:30</td>
                                        <td className="px-2 md:px-4 py-2">20:45</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 15 (*)</td>
                                        <td className="px-2 md:px-4 py-2">20:45</td>
                                        <td className="px-2 md:px-4 py-2">21:00</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 21</td>
                                        <td className="px-2 md:px-4 py-2">21:00</td>
                                        <td className="px-2 md:px-4 py-2">21:15</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 23 (*)</td>
                                        <td className="px-2 md:px-4 py-2">21:30</td>
                                        <td className="px-2 md:px-4 py-2">21:45</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 17 (*)</td>
                                        <td className="px-2 md:px-4 py-2">22:00</td>
                                        <td className="px-2 md:px-4 py-2">22:15</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div className="w-full sm:w-1/2 mt-4 sm:mt-0">
                            <div className="bg-pink-100 p-2 rounded-md">
                                <div className="flex items-center mb-4">
                                    <h4 className="text-lg font-semibold flex items-center">
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                             stroke-width="1.5" stroke="currentColor" className="w-6 h-6">
                                            <path stroke-linecap="round" stroke-linejoin="round"
                                                  d="M6.75 3v2.25M17.25 3v2.25M3 18.75V7.5a2.25 2.25 0 0 1 2.25-2.25h13.5A2.25 2.25 0 0 1 21 7.5v11.25m-18 0A2.25 2.25 0 0 0 5.25 21h13.5A2.25 2.25 0 0 0 21 18.75m-18 0v-7.5A2.25 2.25 0 0 1 5.25 9h13.5A2.25 2.25 0 0 1 21 11.25v7.5m-9-6h.008v.008H12v-.008ZM12 15h.008v.008H12V15Zm0 2.25h.008v.008H12v-.008ZM9.75 15h.008v.008H9.75V15Zm0 2.25h.008v.008H9.75v-.008ZM7.5 15h.008v.008H7.5V15Zm0 2.25h.008v.008H7.5v-.008Zm6.75-4.5h.008v.008h-.008v-.008Zm0 2.25h.008v.008h-.008V15Zm0 2.25h.008v.008h-.008v-.008Zm2.25-4.5h.008v.008H16.5v-.008Zm0 2.25h.008v.008H16.5V15Z"/>
                                        </svg>
                                        <span className="text text-sm sm:text-sm lg:text-base">{t('returnTrip')}</span>
                                    </h4>
                                </div>
                                <table className="min-w-full bg-white border border-gray-200 text-sm sm:text-sm lg:text-base">
                                    <thead className="">
                                    <tr className="bg-gray-200">
                                        <th className="px-2 md:px-4 py-2" style={{width: '192.292px'}}>Mã chuyến</th>
                                        <th className="px-2 md:px-4 py-2" style={{width: '106.542px'}}>Bình An</th>
                                        <th className="px-2 md:px-4 py-2" style={{width: '141.667px'}}>Bạch Đằng</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 2 (*)</td>
                                        <td className="px-2 md:px-4 py-2">16:30</td>
                                        <td className="px-2 md:px-4 py-2">16:45</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 4 (*)</td>
                                        <td className="px-2 md:px-4 py-2">17:00</td>
                                        <td className="px-2 md:px-4 py-2">17:15</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 6</td>
                                        <td className="px-2 md:px-4 py-2">17:30</td>
                                        <td className="px-2 md:px-4 py-2">17:45</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 8</td>
                                        <td className="px-2 md:px-4 py-2">18:00</td>
                                        <td className="px-2 md:px-4 py-2">18:15</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 10</td>
                                        <td className="px-2 md:px-4 py-2">18:30</td>
                                        <td className="px-2 md:px-4 py-2">18:45</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 12</td>
                                        <td className="px-2 md:px-4 py-2">19:00</td>
                                        <td className="px-2 md:px-4 py-2">19:15</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 14</td>
                                        <td className="px-2 md:px-4 py-2">19:30</td>
                                        <td className="px-2 md:px-4 py-2">19:45</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 16</td>
                                        <td className="px-2 md:px-4 py-2">20:00</td>
                                        <td className="px-2 md:px-4 py-2">20:15</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 14 (*)</td>
                                        <td className="px-2 md:px-4 py-2">20:15</td>
                                        <td className="px-2 md:px-4 py-2">20:30</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 18</td>
                                        <td className="px-2 md:px-4 py-2">20:30</td>
                                        <td className="px-2 md:px-4 py-2">20:45</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 20</td>
                                        <td className="px-2 md:px-4 py-2">21:00</td>
                                        <td className="px-2 md:px-4 py-2">21:15</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 16 (*)</td>
                                        <td className="px-2 md:px-4 py-2">21:15</td>
                                        <td className="px-2 md:px-4 py-2">21:30</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 22</td>
                                        <td className="px-2 md:px-4 py-2">21:30</td>
                                        <td className="px-2 md:px-4 py-2">21:45</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Bình An Trip 24 (*)</td>
                                        <td className="px-2 md:px-4 py-2">22:00</td>
                                        <td className="px-2 md:px-4 py-2">22:15</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 18 (*)</td>
                                        <td className="px-2 md:px-4 py-2">22:30</td>
                                        <td className="px-2 md:px-4 py-2">22:45</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section className="p-4">
                <div className="">
                    <h2 className="text-sm sm:text-sm lg:text-base font-bold">{t('enhancedTrips')}</h2>
                    <p>{t('enhancedTripsDescription')}</p>
                </div>
            </section>
            <section className="px-4">
                <div className="container mx-auto">
                    <div className="flex flex-wrap">
                        <div className="w-full sm:w-1/2">
                            <div className="bg-blue-100 p-2 rounded-md sm:mr-2">
                                <div className="flex items-center mb-4">
                                    <h4 className="text-lg font-semibold flex items-center">
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                             stroke-width="1.5" stroke="currentColor" className="w-6 h-6">
                                            <path stroke-linecap="round" stroke-linejoin="round"
                                                  d="M6.75 3v2.25M17.25 3v2.25M3 18.75V7.5a2.25 2.25 0 0 1 2.25-2.25h13.5A2.25 2.25 0 0 1 21 7.5v11.25m-18 0A2.25 2.25 0 0 0 5.25 21h13.5A2.25 2.25 0 0 0 21 18.75m-18 0v-7.5A2.25 2.25 0 0 1 5.25 9h13.5A2.25 2.25 0 0 1 21 11.25v7.5m-9-6h.008v.008H12v-.008ZM12 15h.008v.008H12V15Zm0 2.25h.008v.008H12v-.008ZM9.75 15h.008v.008H9.75V15Zm0 2.25h.008v.008H9.75v-.008ZM7.5 15h.008v.008H7.5V15Zm0 2.25h.008v.008H7.5v-.008Zm6.75-4.5h.008v.008h-.008v-.008Zm0 2.25h.008v.008h-.008V15Zm0 2.25h.008v.008h-.008v-.008Zm2.25-4.5h.008v.008H16.5v-.008Zm0 2.25h.008v.008H16.5V15Z"/>
                                        </svg>
                                        <span className="text text-sm sm:text-sm lg:text-base">{t('outboundTrip')}</span>
                                    </h4>
                                </div>
                                <table className="min-w-full bg-white border border-gray-200 text-sm sm:text-sm lg:text-base">
                                    <thead>
                                    <tr className="bg-gray-200">
                                        <th className="px-2 md:px-4 py-2" style={{width: '64.1458px'}}></th>
                                        <th className="px-2 md:px-4 py-2" style={{width: '129.438px'}}>Bạch Đằng</th>
                                        <th className="px-2 md:px-4 py-2" style={{width: '96.7396px'}}>Bình An</th>
                                        <th className="px-2 md:px-4 py-2" style={{width: '114.177px'}}>Thanh Đa</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 1</td>
                                        <td className="px-2 md:px-4 py-2">9:30</td>
                                        <td className="px-2 md:px-4 py-2">9:45</td>
                                        <td className="px-2 md:px-4 py-2">10:02</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 3</td>
                                        <td className="px-2 md:px-4 py-2">11:00</td>
                                        <td className="px-2 md:px-4 py-2">11:15</td>
                                        <td className="px-2 md:px-4 py-2">11:32</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 5</td>
                                        <td className="px-2 md:px-4 py-2">11:30</td>
                                        <td className="px-2 md:px-4 py-2">11:45</td>
                                        <td className="px-2 md:px-4 py-2">12:02</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 7</td>
                                        <td className="px-2 md:px-4 py-2">13:30</td>
                                        <td className="px-2 md:px-4 py-2">13:45</td>
                                        <td className="px-2 md:px-4 py-2">14:02</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 9</td>
                                        <td className="px-2 md:px-4 py-2">14:00</td>
                                        <td className="px-2 md:px-4 py-2">14:15</td>
                                        <td className="px-2 md:px-4 py-2">14:32</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 11</td>
                                        <td className="px-2 md:px-4 py-2">15:10</td>
                                        <td className="px-2 md:px-4 py-2">15:25</td>
                                        <td className="px-2 md:px-4 py-2">15:42</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div className="w-full sm:w-1/2 mt-4 sm:mt-0">
                            <div className="bg-pink-100 p-2 rounded-md">
                                <div className="flex items-center mb-4">
                                    <h4 className="text-lg font-semibold flex items-center">
                                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                             stroke-width="1.5" stroke="currentColor" className="w-6 h-6">
                                            <path stroke-linecap="round" stroke-linejoin="round"
                                                  d="M6.75 3v2.25M17.25 3v2.25M3 18.75V7.5a2.25 2.25 0 0 1 2.25-2.25h13.5A2.25 2.25 0 0 1 21 7.5v11.25m-18 0A2.25 2.25 0 0 0 5.25 21h13.5A2.25 2.25 0 0 0 21 18.75m-18 0v-7.5A2.25 2.25 0 0 1 5.25 9h13.5A2.25 2.25 0 0 1 21 11.25v7.5m-9-6h.008v.008H12v-.008ZM12 15h.008v.008H12V15Zm0 2.25h.008v.008H12v-.008ZM9.75 15h.008v.008H9.75V15Zm0 2.25h.008v.008H9.75v-.008ZM7.5 15h.008v.008H7.5V15Zm0 2.25h.008v.008H7.5v-.008Zm6.75-4.5h.008v.008h-.008v-.008Zm0 2.25h.008v.008h-.008V15Zm0 2.25h.008v.008h-.008v-.008Zm2.25-4.5h.008v.008H16.5v-.008Zm0 2.25h.008v.008H16.5V15Z"/>
                                        </svg>
                                        <span className="text text-sm sm:text-sm lg:text-base">{t('returnTrip')}</span>
                                    </h4>
                                </div>
                                <table className="table-auto w-full text-left">
                                    <thead>
                                    <tr className="bg-gray-200">
                                        <th className="px-2 md:px-4 py-2" style={{width: '65.5312px'}}></th>
                                        <th className="px-2 md:px-4 py-2" style={{width: '113.688px'}}>Thanh Đa</th>
                                        <th className="px-2 md:px-4 py-2" style={{width: '96.3333px'}}>Bình An</th>
                                        <th className="px-2 md:px-4 py-2" style={{width: '128.948px'}}>Bạch Đằng</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 2</td>
                                        <td className="px-2 md:px-4 py-2">10:20</td>
                                        <td className="px-2 md:px-4 py-2">10:37</td>
                                        <td className="px-2 md:px-4 py-2">10:52</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 4</td>
                                        <td className="px-2 md:px-4 py-2">12:00</td>
                                        <td className="px-2 md:px-4 py-2">12:17</td>
                                        <td className="px-2 md:px-4 py-2">12:32</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 6</td>
                                        <td className="px-2 md:px-4 py-2">12:30</td>
                                        <td className="px-2 md:px-4 py-2">12:47</td>
                                        <td className="px-2 md:px-4 py-2">13:02</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 8</td>
                                        <td className="px-2 md:px-4 py-2">14:30</td>
                                        <td className="px-2 md:px-4 py-2">14:47</td>
                                        <td className="px-2 md:px-4 py-2">15:02</td>
                                    </tr>
                                    <tr className="bg-white border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 10</td>
                                        <td className="px-2 md:px-4 py-2">15:00</td>
                                        <td className="px-2 md:px-4 py-2">15:17</td>
                                        <td className="px-2 md:px-4 py-2">15:32</td>
                                    </tr>
                                    <tr className="bg-gray-50 border-b">
                                        <td className="px-2 md:px-4 py-2">Plus 12</td>
                                        <td className="px-2 md:px-4 py-2">15:50</td>
                                        <td className="px-2 md:px-4 py-2">16:07</td>
                                        <td className="px-2 md:px-4 py-2">16:22</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section className="px-4">
                <div className="">
                    <h2 className="font-bold text-sm sm:text-sm lg:text-base">{t('freeFareNote')}</h2>
                    {/* <p>{t('freeFareNote')}</p> */}
                </div>
            </section>
        </div>
    );
}

export default Lich;

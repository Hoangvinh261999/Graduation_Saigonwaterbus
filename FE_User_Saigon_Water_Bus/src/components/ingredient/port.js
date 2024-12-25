import React from 'react';
import { MapPinIcon, AcademicCapIcon, TruckIcon } from '@heroicons/react/24/solid';
import { useTranslation } from 'react-i18next';

const Port = () => {
    const { t } = useTranslation();

    return (
        <div className="">
            <div className='location'>
                <div className="p-4">
                    <div className=" p-4 rounded-lg">
                        <h1 className="text-xl font-bold mb-2">{t('address')}</h1>
                        <div className="relative w-full h-64 mb-4">
                            <iframe
                                title="map"
                                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3918.788542911078!2d106.72670577115123!3d10.827488065730288!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175262c44eab767%3A0xc62fe15f779e1d85!2zNzAgxJAuIHPhu5EgMTAsIEhp4buHcCBCw6xuaCBDaMOhbmgsIFRo4bunIMSQ4bupYywgVGjDoG5oIHBo4buRIEjhu5MgQ2jDrSBNaW5oLCBWaWV0bmFt!5e0!3m2!1sen!2s!4v1716696904330!5m2!1sen!2s"
                                className="absolute inset-0 w-full h-full border-0"
                                allowFullScreen=""
                                aria-hidden="false"
                                tabIndex="0"
                            ></iframe>
                        </div>
                        <div className='flex'>
                            <div className='lg:w-6/12 px-2'>
                                <h2 className="text-lg font-bold mb-2">{t('nearby_places')}</h2>
                                <ul className="mb-4">
                                    <li className="mb-2"><MapPinIcon className="w-6 h-6 inline-block mr-2"/>{t('children_playground')}</li>
                                    <li><MapPinIcon className="w-6 h-6 inline-block mr-2"/>{t('restaurant')}</li>
                                </ul>
                            </div>
                            <div className='lg:w-6/12 px-4'>
                                <h2 className="text-lg font-bold mb-2">{t('transportation')}</h2>
                                <ul>
                                    <li className="mb-2"><AcademicCapIcon className="w-6 h-6 inline-block mr-2"/>{t('bike_parking')}</li>
                                    <li><TruckIcon className="w-6 h-6 inline-block mr-2"/>{t('car_parking')}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className='gioithieu'>
                <div className="p-4">
                    <div className="container mx-auto grid grid-cols-1 md:grid-cols-2 gap-8">
                        <div className="bg-white p-6 rounded-lg shadow-md">
                            <h2 className="text-2xl font-bold mb-4">{t('introduction')}</h2>
                            <p className="mb-4">{t('introduction_text')}</p>
                        </div>
                        <div className="bg-white p-6 rounded-lg shadow-md">
                            <h2 className="text-2xl font-bold mb-4">{t('tourism_info')}</h2>
                            <p className="mb-4">{t('tourism_text_1')}</p>
                            <p className="mb-4">{t('tourism_text_2')}</p>
                            <p>{t('market_info')}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div className='sodotuyen p-8'>
                <h2 className="text-2xl font-bold mb-4">{t('route_map')}</h2>
                <br/>
                <img loading="lazy" decoding="async" width="1172" height="826"
                     src="https://saigonwaterbus.com/wp-content/uploads/2022/06/maps.jpg"
                     className="attachment-full size-full" alt=""
                     srcSet="https://saigonwaterbus.com/wp-content/uploads/2022/06/maps.jpg 1172w,
                     https://saigonwaterbus.com/wp-content/uploads/2022/06/maps-300x211.jpg 300w,
                     https://saigonwaterbus.com/wp-content/uploads/2022/06/maps-1024x722.jpg 1024w,
                     https://saigonwaterbus.com/wp-content/uploads/2022/06/maps-768x541.jpg 768w"
                     sizes="(max-width: 1172px) 100vw, 1172px"/>
            </div>
        </div>
    );
};

export default Port;
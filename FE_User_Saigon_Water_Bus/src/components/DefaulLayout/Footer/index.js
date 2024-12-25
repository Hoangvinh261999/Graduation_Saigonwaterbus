import './footer.css';
import {useTranslation} from 'react-i18next';

const Footer = () => {
    const {t} = useTranslation();

    return (<div className="footer text-sm lg:text-base bg-gray-100">
            <footer className="bg-black-200 pt-8 pb-6">
                <div className="container mx-auto px-4">
                    <div className="flex flex-wrap text-left lg:text-left">
                        <div className="lg:w-6/12 px-4 justify-center">
                            <div className="flex justify-center">
                                <img className="Style_Image" alt="" src="/logo_footer.png"/>
                            </div>
                            <div className="justify-center md:justify-normal font-semibold">
                                {t('footer.company_info')}
                            </div>
                        </div>
                        <div className="w-full lg:w-6/12 justify-center">
                            <div className="flex flex-wrap justify-between mt-2">
                                <div className="w-1/2 lg:w-3/12 px-4">
                  <span className="block uppercase text-blueGray-500 text-sm font-bold mb-2">
                    {t('footer.info')}
                  </span>
                                    <ul className="list-unstyled">
                                        <li>{t('footer.about_us')}</li>
                                        <li>{t('footer.partners')}</li>
                                        <li>{t('footer.careers')}</li>
                                    </ul>
                                </div>
                                <div className="w-1/2 lg:w-3/12 px-4 mb-4">
                  <span className="block uppercase text-blueGray-500 text-sm font-bold mb-2">
                    {t('footer.services')}
                  </span>
                                    <ul className="list-unstyled">
                                        <li>{t('footer.water_bus')}</li>
                                    </ul>
                                </div>
                                <div className="w-1/2 lg:w-3/12 px-4 mb-4">
                  <span className="block uppercase text-blueGray-500 text-sm font-bold mb-2">
                    {t('footer.policy')}
                  </span>
                                    <ul className="list-unstyled">
                                        <li>{t('footer.terms_and_conditions')}</li>
                                        <li>{t('footer.terms_of_use')}</li>
                                        <li>{t('footer.privacy_policy')}</li>
                                    </ul>
                                </div>
                                <div className="w-1/2 lg:w-3/12 px-4 mb-4">
                  <span className="block uppercase text-blueGray-500 text-sm font-bold mb-2">
                    {t('footer.contact')}
                  </span>
                                    <ul className="list-unstyled">
                                        <li>{t('footer.main_office')}</li>
                                        <li>{t('footer.sales_cooperation')}</li>
                                        <li>{t('footer.faq')}</li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <hr className="my-1 border-blueGray-300"/>
                </div>
            </footer>
        </div>);
};

export default Footer;

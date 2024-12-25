import React from 'react';
import { useTranslation } from 'react-i18next';

const LanguageSwitcher = () => {
    const { i18n } = useTranslation();

    const changeLanguage = (event) => {
        i18n.changeLanguage(event.target.value);
    };

    return (
        <div className='ml-2 content-center'>
            <select
                value={i18n.language}
                onChange={changeLanguage}
                className="border rounded text-xs font-semibold bg-slate-200"
            >
                <option value="vi">Tiếng Việt</option>
                <option value="en">English</option>
            </select>
        </div>
    );
};

export default LanguageSwitcher;
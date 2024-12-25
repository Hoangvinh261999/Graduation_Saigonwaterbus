import React, { useState } from 'react';
import { useTranslation } from "react-i18next";

function TabComponent() {
    const [activeTab, setActiveTab] = useState(1);
    const handleTabClick = (tabIndex) => {
        setActiveTab(tabIndex);
    };
    const { t } = useTranslation();

    const tabData = [
        {
            label: t("tab.image"),
            content: (
                <div className='flex content-center justify-center'>
                    <img alt='' src='https://static.vexere.com/production/images/1675673512101.jpeg' />
                </div>
            ),
        },
        {
            label: t("tab.utilities"),
            content: (
                <div className='flex flex-col items-center'>
                    <img className='w-6/12' alt='' src='https://globalopentour.com/wp-content/content/images/du-thuyen-Paradise-vinh-ha-long-2.jpg' />
                </div>
            ),
        },
        {
            label: t("tab.cancel"),
            content: (
                <div className="cancellation-policy-container p-4">
                    <div className="cancellation-policy-title text-lg font-bold mb-2">
                        {t("tab.cancel")}
                    </div>
                    <div className="cancellation-policy-timeline mb-4 relative">
                        <div className="cancellation-policy-period items-center space-x-2">
                            <div className="h-4 bg-red-500 w-full"></div>
                            <p className='text-center'>{t("tab.notAllow")}</p>
                        </div>
                        <div className="flex items-center space-x-2 absolute top-0 right-8">
                            <div className="circle w-4 h-4 bg-white rounded-full"></div>
                        </div>
                    </div>
                    <div className="cancellation-policy-note mt-4 text-sm">
                        <b>{t("tab.note")}: </b>{t("tab.notDetail")}
                    </div>
                </div>
            ),
        },
    ];

    return (
        <div className="flex flex-col w-full">
            <hr />
            <div className="flex justify-center content-center">
                <div className="flex space-x-8">
                    {tabData.map((tab, index) => (
                        <div
                            key={index}
                            className={`cursor-pointer ${activeTab === index + 1 ? 'underline' : ''}`}
                            onClick={() => handleTabClick(index + 1)}
                        >
                            {tab.label}
                        </div>
                    ))}
                </div>
            </div>
            <div>
                {tabData.map((tab, index) => (
                    <div
                        key={index}
                        className={`${activeTab === index + 1 ? '' : 'hidden'}`}
                    >
                        {tab.content}
                    </div>
                ))}
            </div>
        </div>
    );
}

export default TabComponent;

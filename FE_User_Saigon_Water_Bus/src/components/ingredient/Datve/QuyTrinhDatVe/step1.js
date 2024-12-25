import React from 'react';
import {useTranslation} from 'react-i18next';
import SeatingChart from '../ghetau';

const Step1 = ({nextStep, clickedSeats, setClickedSeats, chuyenTau, seatLabels}) => {
    // Trong Step1, bạn có thể sử dụng clickedSeats để hiển thị các ghế đã được chọn.
    const {t} = useTranslation();
    return (
        <div className='container mx-auto'>
            <SeatingChart seatLabels={seatLabels} clickedSeats={clickedSeats} setClickedSeats={setClickedSeats}
                          chuyenTau={chuyenTau}/>
            <div className='flex justify-end'>
                <button
                    className={`bg-green-500 text-white font-bold p-2 text-sm 2xl:text-base rounded flex items-center ${clickedSeats.length === 0 || clickedSeats === null ? 'disabled bg-slate-100' : ''}`}
                    onClick={clickedSeats.length === 0 || clickedSeats === null ? null : nextStep}
                >
                    {t('continue')}
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                         stroke="currentColor" className="w-6 h-6 ml-2">
                        <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 4.5l7.5 7.5-7.5 7.5"/>
                    </svg>
                </button>
            </div>
        </div>
    );
};

export default Step1;

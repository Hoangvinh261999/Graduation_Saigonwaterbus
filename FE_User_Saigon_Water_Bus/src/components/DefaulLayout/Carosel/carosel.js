import React, { useState } from 'react';
import { ChevronLeftIcon, ChevronRightIcon } from '@heroicons/react/24/solid'; // Use 'outline' for outline icons// Import các icon từ Heroicons

const Carousel = ({ images, width, height }) => {
    const [currentIndex, setCurrentIndex] = useState(0);

    const prevSlide = () => {
        setCurrentIndex((prevIndex) =>
            prevIndex === 0 ? images.length - 1 : prevIndex - 1
        );
    };

    const nextSlide = () => {
        setCurrentIndex((prevIndex) =>
            prevIndex === images.length - 1 ? 0 : prevIndex + 1
        );
    };

    return (
        <div className={`relative ${width} ${height} mx-auto`}>
            <div className={`overflow-hidden relative ${height}`}>
                <div
                    className="whitespace-nowrap transition-transform duration-500"
                    style={{ transform: `translateX(-${currentIndex * 100}%)` }}
                >
                    {images.map((image, index) => (
                        <div
                            key={index}
                            className={`inline-block ${width} ${height} bg-center bg-cover`}
                            style={{ backgroundImage: `url(${image})` }}
                        ></div>
                    ))}
                </div>
            </div>
            <button
                onClick={prevSlide}
                className="absolute top-1/2 transform -translate-y-1/2 left-0 p-2"
            >
                <ChevronLeftIcon className="w-8 h-8 text-gray-700" /> {/* Sử dụng component icon từ Heroicons */}
            </button>
            <button
                onClick={nextSlide}
                className="absolute top-1/2 transform -translate-y-1/2 right-0 p-2"
            >
                <ChevronRightIcon className="w-8 h-8 text-gray-700" /> {/* Sử dụng component icon từ Heroicons */}
            </button>
        </div>
    );
};

export default Carousel;

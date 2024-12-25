import React, {useState} from "react";

const Popup = ({message}) => {
    const [isVisible, setIsVisible] = useState(true);

    if (!isVisible || !message) return null;

    const handleClose = () => {
        setIsVisible(false);
    };

    return (
        <div className="fixed inset-0 bg-gray-600 bg-opacity-50 flex items-center justify-center">
            <div className="bg-white rounded-lg shadow-lg p-8 w-96">
                <h2 className="text-2xl font-bold mb-4">{message}</h2>
                <div className="flex justify-end">
                    <button
                        onClick={handleClose}
                        className="px-4 py-2 bg-blue-600 text-white rounded-lg"
                    >
                        Đóng
                    </button>
                </div>
            </div>
        </div>
    );
};

export default Popup;

//
//  MKShape.h
//  MapKit
//
//  Copyright 2010 Apple, Inc. All rights reserved.
//

#if __IPHONE_4_0 <= __IPHONE_OS_VERSION_MAX_ALLOWED

#import <Foundation/Foundation.h>
#import <MapKit/MKAnnotation.h>

@interface MKShape : NSObject <MKAnnotation> {
@package
    NSString *_title;
    NSString *_subtitle;
}

@property (copy) NSString *title;
@property (copy) NSString *subtitle;

@end

#endif

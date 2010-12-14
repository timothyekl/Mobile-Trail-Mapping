//
//  MKCircleView.h
//  MapKit
//
//  Copyright 2010 Apple, Inc. All rights reserved.
//

#if __IPHONE_4_0 <= __IPHONE_OS_VERSION_MAX_ALLOWED

#import <UIKit/UIKit.h>

#import <MapKit/MKCircle.h>
#import <MapKit/MKOverlayPathView.h>

@interface MKCircleView : MKOverlayPathView

- (id)initWithCircle:(MKCircle *)circle;

@property (nonatomic, readonly) MKCircle *circle;

@end

#endif
